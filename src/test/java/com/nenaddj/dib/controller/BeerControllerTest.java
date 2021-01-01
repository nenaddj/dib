package com.nenaddj.dib.controller;

import com.nenaddj.dib.model.mapper.BeerMapper;
import com.nenaddj.dib.service.BeerServiceImpl;
import com.sun.security.auth.UserPrincipal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BeerController.class})
@ExtendWith(SpringExtension.class)
public class BeerControllerTest {
    @Autowired
    private BeerController beerController;

    @MockBean
    private BeerMapper beerMapper;

    @MockBean
    private BeerServiceImpl beerServiceImpl;

    @Test
    public void testDelete() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/beers/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.beerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testDelete2() throws Exception {
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/beers/{id}", 1L);
        deleteResult.principal(new UserPrincipal("principal"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.beerController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetAll() throws Exception {
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("https://example.org/example", "foo",
                "foo", "foo");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("pageable", String.valueOf((Object) null));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.beerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetBeer() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/beers/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.beerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetBeer2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/beers/{id}", 1L);
        getResult.contentType("Not all who wander are lost");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.beerController).build().perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testInsertBeers() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/beers/{count}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.beerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testInsertBeers2() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/beers/{count}", 1);
        postResult.contentType("Not all who wander are lost");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.beerController)
                .build()
                .perform(postResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

