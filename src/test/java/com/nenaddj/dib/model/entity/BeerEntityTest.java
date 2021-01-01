package com.nenaddj.dib.model.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertSame;

public class BeerEntityTest {
    @Test
    public void testGetMeanTemperature() {
        BigDecimal actualMeanTemperature = (new BeerEntity()).getMeanTemperature();
        assertSame(actualMeanTemperature.ZERO, actualMeanTemperature);
    }
}

