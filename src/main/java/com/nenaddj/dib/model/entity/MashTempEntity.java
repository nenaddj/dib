package com.nenaddj.dib.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class MashTempEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal temperature;
    @ManyToOne
    private BeerEntity beer;

    public MashTempEntity() {
    }

    public MashTempEntity(BeerEntity beerEntity, BigDecimal temp) {
        this.beer = beerEntity;
        this.temperature = temp;
    }
}
