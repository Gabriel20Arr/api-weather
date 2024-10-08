package com.apiclima.apiclima.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

//! Implementamos los datos que devolvera nuestras peticiones 
@Data
@NoArgsConstructor
public class ForecasteResponseDto {
    private CityDto city;
    private int message;
    private int cnt;
    private List<list> list;

    @Data
    public static class list {
        private long dt;
        private mainData main;
        private List<weather> weather;
        private wind wind;
        private int visibility;
        private double pop;
        private sys sys;
        private String dt_txt;
    }

    @Data
    public static class mainData {
        private float temp;
        private float feels_like;
        private float temp_min;
        private float temp_max;
        private int humidity;
    }

    @Data
    public static class weather {
        private int id;
        private String main;
        private String description;
        private String icon;

    }

    @Data
    public static class wind {
        private double speed;
        private int deg;
        private double gust;
    }

    @Data
    public static class sys {
        private String pod;
        private String change = "---- cambiamos ----";
    }

}
