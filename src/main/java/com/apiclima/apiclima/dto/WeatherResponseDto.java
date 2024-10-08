package com.apiclima.apiclima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

//! Implementamos los datos que devolvera nuestras peticiones 
@Data
@NoArgsConstructor
public class WeatherResponseDto {
    private String name;
    private Coord coord;
    private Weather[] weather;
    private String base;
    private Main main;
    private Wind wind;
    private long dt;
    private Sys sys;

    // Getters y Setters

    @Data
    public static class Coord {
        private double lon; // Longitud
        private double lat; // Latitud
    }

    @Data
    public static class Weather {
        private String main; // Descripción principal del clima
        private String description; // Descripción detallada
        private String icon; // Icono del clima
    }

    @Data
    public static class Main {
        private double temp; // Temperatura
        @JsonProperty("temp_min")
        private double tempMin; // Temperatura mínima
        @JsonProperty("temp_max")
        private double tempMax; // Temperatura máxima
        private int humidity; // Humedad
    }
    
    @Data
    public static class Wind {
        private double speed; // Velocidad del viento
        private int deg; // Dirección del viento
    }
    
    @Data
    public static class Sys {
        private String country; // Código del país
        private long sunrise; // Hora del amanecer
        private long sunset; // Hora del atardecer
    }

}

