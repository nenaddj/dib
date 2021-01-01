package com.nenaddj.dib.model.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BeerDTOTest {
    @Test
    public void testCanEqual() {
        assertFalse((new BeerDTO()).canEqual("other"));
    }

    @Test
    public void testCanEqual2() {
        BeerDTO beerDTO = new BeerDTO();
        assertTrue(beerDTO.canEqual(new BeerDTO()));
    }

    @Test
    public void testEquals() {
        assertNotEquals((new BeerDTO()), "o");
    }

    @Test
    public void testEquals10() {
        BigDecimal meanTemperature = BigDecimal.valueOf(42L);
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setMeanTemperature(meanTemperature);
        assertNotEquals(beerDTO, (new BeerDTO()));
    }

    @Test
    public void testEquals2() {
        BeerDTO beerDTO = new BeerDTO();
        assertEquals(new BeerDTO(), beerDTO);
    }

    @Test
    public void testEquals3() {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setId(123L);
        assertNotEquals(new BeerDTO(), beerDTO);
    }

    @Test
    public void testEquals4() {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setDescription("The characteristics of someone or something");
        assertNotEquals(new BeerDTO(), beerDTO);
    }

    @Test
    public void testEquals5() {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setId(123L);
        assertNotEquals(beerDTO, (new BeerDTO()));
    }

    @Test
    public void testEquals6() {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setDescription("The characteristics of someone or something");
        assertNotEquals(beerDTO, (new BeerDTO()));
    }

    @Test
    public void testEquals7() {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setName("Name");
        assertNotEquals(new BeerDTO(), beerDTO);
    }

    @Test
    public void testEquals8() {
        BigDecimal meanTemperature = BigDecimal.valueOf(42L);
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setMeanTemperature(meanTemperature);
        assertNotEquals(new BeerDTO(), beerDTO);
    }

    @Test
    public void testEquals9() {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setName("Name");
        assertNotEquals(beerDTO, (new BeerDTO()));
    }

    @Test
    public void testHashCode() {
        assertEquals(21100921, (new BeerDTO()).hashCode());
    }

    @Test
    public void testHashCode2() {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setId(123L);
        assertEquals(37531241, beerDTO.hashCode());
    }

    @Test
    public void testHashCode3() {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setDescription("The characteristics of someone or something");
        assertEquals(1321760895, beerDTO.hashCode());
    }

    @Test
    public void testHashCode4() {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setName("Name");
        assertEquals(-143588359, beerDTO.hashCode());
    }

    @Test
    public void testHashCode5() {
        BigDecimal meanTemperature = BigDecimal.valueOf(42L);
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setMeanTemperature(meanTemperature);
        assertEquals(21102180, beerDTO.hashCode());
    }

    @Test
    public void testSetDescription() {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setDescription("The characteristics of someone or something");
        assertEquals("The characteristics of someone or something", beerDTO.getDescription());
    }

    @Test
    public void testSetId() {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setId(123L);
        assertEquals(123L, beerDTO.getId().longValue());
    }

    @Test
    public void testSetName() {
        BeerDTO beerDTO = new BeerDTO();
        beerDTO.setName("Name");
        assertEquals("Name", beerDTO.getName());
    }

    @Test
    public void testToString() {
        assertEquals("BeerDTO(id=null, name=null, description=null, meanTemperature=null)", (new BeerDTO()).toString());
    }
}

