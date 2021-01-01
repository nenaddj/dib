package com.nenaddj.dib.model.mapper;

import com.nenaddj.dib.external.model.BeerExt;
import com.nenaddj.dib.model.dto.BeerDTO;
import com.nenaddj.dib.model.entity.BeerEntity;
import com.nenaddj.dib.model.entity.MashTempEntity;
import com.nenaddj.dib.repository.BeerRepository;
import com.nenaddj.dib.service.BeerServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;

public class BeerMapperTest {
    @Test
    public void testEntityToDto() {
        BeerEntity beerEntity = new BeerEntity();
        beerEntity.setTemperatures(new ArrayList<>());
        beerEntity.setId(123L);
        beerEntity.setName("Name");
        beerEntity.setMeanTemperature(BigDecimal.valueOf(42L));
        beerEntity.setDescription("The characteristics of someone or something");

        BeerDTO beerDTO = BeerMapper.entityToDto(beerEntity);

        Assert.assertEquals(beerEntity.getId(),beerDTO.getId());
        Assert.assertEquals(beerEntity.getName(),beerDTO.getName());
        Assert.assertEquals(beerEntity.getDescription(),beerDTO.getDescription());
        Assert.assertEquals(beerEntity.getMeanTemperature(),beerDTO.getMeanTemperature());
        Assert.assertNotSame(beerEntity.getTemperatures(), null);
    }

    @Test
    public void testExtToEntity() {
        BeerRepository mockRepo = mock(BeerRepository.class);
        BeerServiceImpl service = new BeerServiceImpl(mockRepo, null);

        BeerExt beerExt = new BeerExt();
        beerExt.setId(123L);
        beerExt.setName("Name");
        beerExt.setDescription("The characteristics of someone or something");

        BeerExt.Method method = new BeerExt.Method();
        BeerExt.Temp temp = new BeerExt.Temp();
        BeerExt.Mash_temp mash_temp = new BeerExt.Mash_temp();

        temp.setValue(BigDecimal.valueOf(55));
        temp.setUnit("celsius");
        mash_temp.setTemp(temp);

        method.setMash_temp(new ArrayList<>());
        method.getMash_temp().add(mash_temp);

        beerExt.setMethod(method);

        BeerEntity beerEntity = BeerMapper.extToEntity(beerExt);
        List<MashTempEntity> temps =
                beerExt.getTemperatures()
                        .stream()
                        .map(t -> new MashTempEntity(beerEntity, t))
                        .collect(Collectors.toList());
        beerEntity.setTemperatures(temps);
        mockRepo.save(beerEntity);

        Assert.assertEquals(beerExt.getId(), beerEntity.getId());
        Assert.assertEquals(beerExt.getName(), beerEntity.getName());
        Assert.assertEquals(beerExt.getDescription(), beerEntity.getDescription());

        List<BigDecimal> entityTemps = beerEntity.getTemperatures().stream()
                .map(MashTempEntity::getTemperature)
                .collect(Collectors.toList());
        Assert.assertEquals(beerExt.getTemperatures(), entityTemps);
    }
}

