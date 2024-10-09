package com.apiclima.apiclima.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//! Implementamos los datos que devolvera nuestras peticiones 
@Data
@NoArgsConstructor
public class AirPollutionResponseDto {
    private CoordDTO coord;
    private List<PollutionDataDTO> list;
    
    @Data
    public static class CoordDTO {
        private double lon;
        private double lat;
    }

    @Data
    @Getter
    public static class PollutionDataDTO {
        private MainDTO main;
        private ComponentsDTO components;
        private long dt;
    }
    
    @Data
    @Getter
    public static class MainDTO {
        private int aqi;  // Índice de calidad del aire (AQI)
    }

    @Data
    public static class ComponentsDTO {
        private double co;      // Monóxido de carbono
        private double no;      // Monóxido de nitrógeno
        private double no2;     // Dióxido de nitrógeno
        private double o3;      // Ozono
        private double so2;     // Dióxido de azufre
        private double pm2_5;   // Partículas finas PM2.5
        private double pm10;    // Partículas PM10
        private double nh3;     // Amoníaco

    }

}
