package com.apiclima.apiclima.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apiclima.apiclima.config.CacheConfig;
import com.apiclima.apiclima.dto.AirPollutionResponseDto;
import com.apiclima.apiclima.dto.ForecasteResponseDto;
import com.apiclima.apiclima.dto.WeatherResponseDto;

//! Implementamos la logica para consumir la API 
@Import({ CacheConfig.class })
@ImportAutoConfiguration(classes = {
    CacheAutoConfiguration.class,
    RedisAutoConfiguration.class
})
@Service
public class WeatherService {
    @Value("${apikey}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    };

    //? Obtenemos el clima actual por nombre
    @Cacheable("getCurrentWeather")
    public WeatherResponseDto getCurrentWeather(String cityName) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey + "&units=metric";
        try {
            return restTemplate.getForObject(url, WeatherResponseDto.class);
        } catch (Exception e) {
            // Aquí puedes registrar el error o manejarlo de la forma que desees
            System.err.println("Error al obtener el clima para " + cityName + ": " + e.getMessage());
            throw new RuntimeException("Error al obtener el clima para " + cityName + ": " + e.getMessage());
        }
    }

    //? Obtener pronóstico de 5 días de una ciudad por latitud y longitud
    @Cacheable("FiveDayForecast")
    public ForecasteResponseDto getFiveDayForecast(double lat, double lon) {
        String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey + "&units=metric";
        return restTemplate.getForObject(url, ForecasteResponseDto.class);
    }

    //? Obtener datos de contaminación del aire actual por latitud y longitud
    @Cacheable("AirPollutionData")
    public AirPollutionResponseDto getAirPollutionData(double lat, double lon) {
        String url = "http://api.openweathermap.org/data/2.5/air_pollution?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey;
        return restTemplate.getForObject(url, AirPollutionResponseDto.class);
    }
}
