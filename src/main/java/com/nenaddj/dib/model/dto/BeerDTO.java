package com.nenaddj.dib.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BeerDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal meanTemperature;
}