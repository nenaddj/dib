package com.nenaddj.dib.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class BeerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(length = 1000)
    private String description;
    @OneToMany(
            mappedBy = "beer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<MashTempEntity> temperatures = new ArrayList<>();

    private transient BigDecimal meanTemperature;

    public BigDecimal getMeanTemperature() {
        BigDecimal meanTemp = BigDecimal.ZERO;
        if (getTemperatures().size() != 0) {
            for (MashTempEntity mashTempEntity : getTemperatures()) {
                BigDecimal temp = mashTempEntity.getTemperature();
                if (null != temp) {
                    meanTemp = meanTemp.add(temp);
                }
            }
            meanTemp = meanTemp.divide(BigDecimal.valueOf(getTemperatures().size()), 2, RoundingMode.HALF_EVEN);
        }
        return meanTemp;
    }
}