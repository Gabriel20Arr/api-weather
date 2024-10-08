package com.apiclima.apiclima.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiclima.apiclima.dto.AirPollutionResponseDto;
import com.apiclima.apiclima.dto.ForecasteResponseDto;
import com.apiclima.apiclima.dto.WeatherResponseDto;
import com.apiclima.apiclima.services.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


//! Para manejar las solicitudes HTTP y definir endpoints
@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;

    //? Obtener el clima actual por nombre de ciudad
    @GetMapping("/current/{cityName}")
    public WeatherResponseDto getCurrentWeather(@PathVariable String cityName){
        return weatherService.getCurrentWeather(cityName);
    }
    
    //? Obtener el pronóstico de 5 días por latitud y longitud
    @GetMapping("/forecast")
    public ForecasteResponseDto getFiveDayForecast(@RequestParam double lat, @RequestParam double lon) {
        return weatherService.getFiveDayForecast(lat, lon);
    }

    //? Obtener datos de contaminación del aire actual por latitud y longitud
    @GetMapping("/air-pollution")
    public AirPollutionResponseDto getAirPollutionData(@RequestParam double lat, @RequestParam double lon) {
        return weatherService.getAirPollutionData(lat, lon);
    }
}
