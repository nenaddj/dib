package com.nenaddj.dib.external.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nenaddj.dib.external.model.BeerExt;
import netscape.javascript.JSObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@Controller
public class PunkApiController {
    private static final String URL = "https://api.punkapi.com/v2/beers/random";
    private static HttpEntity<JSObject> HTTP_ENTITY;
// TODO analize that
    static {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HTTP_ENTITY = new HttpEntity<>(headers);
    }

    private final RestTemplate restTemplate;

    public PunkApiController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    // TODO srediti kod
    public BeerExt getRandomBeer() throws JsonProcessingException {
        ResponseEntity<BeerExt[]> responseEntity = restTemplate.getForEntity(URL, BeerExt[].class);
        return Objects.requireNonNull(responseEntity.getBody())[0];
    }
}
