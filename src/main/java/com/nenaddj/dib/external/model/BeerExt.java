package com.nenaddj.dib.external.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BeerExt implements Serializable {
    private Long id;
    private String name;
    private String description;

    @JsonProperty("method")
    private Method method;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Method {
        @JsonProperty("mash_temp")
        List<Mash_temp> mash_temp ;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Mash_temp {
        @JsonProperty("temp")
        Temp temp;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Temp {
        private BigDecimal value;
        private String unit;

    }

    public List<BigDecimal> getTemperatures() {
        return getMethod()
                .getMash_temp()
                .stream().map(mp -> mp.getTemp().getValue())
                .collect(Collectors.toList());
    }
}