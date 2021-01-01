package com.nenaddj.dib.service;

import com.nenaddj.dib.model.entity.BeerEntity;
import com.nenaddj.dib.repository.BeerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BeerServiceImplTest {

    @Test
    public void findAll() {

        BeerRepository mockRepo = mock(BeerRepository.class);

        BeerEntity beer = new BeerEntity();
        beer.setId(1L);
        beer.setDescription("desc1");
        beer.setName("name1");
        when(mockRepo.findAll()).thenReturn(Collections.singletonList(beer));

        BeerServiceImpl service = new BeerServiceImpl(mockRepo, null);
        List<BeerEntity> result = service.findAll();
        assertEquals(Collections.singletonList(beer), result);

    }
}
