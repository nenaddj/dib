package com.nenaddj.dib.service;

import com.nenaddj.dib.model.entity.BeerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BeerService {
    Page<BeerEntity> findAll(Pageable pageable);

    BeerEntity findById(Long id);

    void deleteById(Long id);

    Long count();

    Long insertBeers(Integer count);

    List<BeerEntity> findAll();

    BeerEntity save(BeerEntity beerEntity);
}
