package com.nenaddj.dib.model.mapper;

import com.nenaddj.dib.external.model.BeerExt;
import com.nenaddj.dib.model.dto.BeerDTO;
import com.nenaddj.dib.model.entity.BeerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class BeerMapper {

    public static BeerDTO entityToDto(BeerEntity beer){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(beer, BeerDTO.class);
    }

    public static BeerEntity extToEntity(BeerExt ext){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(ext, BeerEntity.class);
    }

    public static Page<BeerDTO> toPageDto(Page<BeerEntity> beerEntities) {
        return beerEntities.map(BeerMapper::entityToDto);
    }
}
