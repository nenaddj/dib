package com.nenaddj.dib.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nenaddj.dib.external.controller.PunkApiController;
import com.nenaddj.dib.external.model.BeerExt;
import com.nenaddj.dib.model.entity.BeerEntity;
import com.nenaddj.dib.model.entity.MashTempEntity;
import com.nenaddj.dib.model.mapper.BeerMapper;
import com.nenaddj.dib.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BeerServiceImpl implements BeerService {

    @Value("${max.beers.in.db}")
    private Long maxBeersInDb;

    private final BeerRepository beerRepository;
    private final PunkApiController punkApiController;

    public BeerServiceImpl(BeerRepository beerRepository, PunkApiController punkApiController) {
        this.beerRepository = beerRepository;
        this.punkApiController = punkApiController;
    }

    @Override
    @Transactional
    public BeerEntity save(BeerEntity beerEntity){
        return beerRepository.save(beerEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BeerEntity> findAll(Pageable pageable) {
        return  beerRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public BeerEntity findById(Long id) {
        return beerRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        beerRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return beerRepository.count();
    }

    @Override
    @Transactional
    public Long insertBeers(Integer count) {
        Long numBeersInDB = count();
        long beersToInsert = Math.min(maxBeersInDb - numBeersInDB, count);
        Long beersToInsertTemp = beersToInsert;
        try {
            while (beersToInsert > 0L) {
                BeerExt beerExt = punkApiController.getRandomBeer();
                if(isUnique(beerExt.getName()) && beerExt.getTemperatures().size() > 0) { // API returns null temperature for some beers
                    BeerEntity ent = BeerMapper.extToEntity(beerExt);
                    List<MashTempEntity> temps = createAll(beerExt.getTemperatures(), ent);
                    ent.setTemperatures(temps);
                    beerRepository.save(ent);
                    System.out.println(beersToInsert + " " + beerExt.getName());
                    beersToInsert--;
                } else {
                    System.out.println("XXXXX="+beerExt.getName());
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return beersToInsertTemp;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BeerEntity> findAll() {
        return beerRepository.findAll();
    }

    private List<MashTempEntity> createAll(final List<BigDecimal> temperaturesInCelsius, final BeerEntity beerEntity) {
        return temperaturesInCelsius
                .stream()
                .map(t -> new MashTempEntity(beerEntity, t))
                .collect(Collectors.toList());
    }

    // TODO Exclude beers if existing in db, based name column
    private Boolean isUnique(String name){
        Set<String> set = findAll().stream()
                .map(BeerEntity::getName)
                .collect(Collectors.toSet());
        return !set.contains(name);
    }
}
