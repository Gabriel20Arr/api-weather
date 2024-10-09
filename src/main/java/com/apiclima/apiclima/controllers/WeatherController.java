package com.apiclima.apiclima.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiclima.apiclima.dto.AirPollutionResponseDto;
import com.apiclima.apiclima.dto.ForecasteResponseDto;
import com.apiclima.apiclima.dto.WeatherResponseDto;
import com.apiclima.apiclima.security.entity.UsuarioPrincipal;
import com.apiclima.apiclima.services.AuditoriaLogService;
import com.apiclima.apiclima.services.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;


//! Para manejar las solicitudes HTTP y definir endpoints
@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;

    @Autowired
    private AuditoriaLogService auditoriaLogService;

    //? Obtener el clima actual por nombre de ciudad
    @GetMapping("/current/{cityName}")
    public WeatherResponseDto getCurrentWeather(@PathVariable String cityName, Principal principal){
        // Lógica para obtener el clima actual
        WeatherResponseDto response = weatherService.getCurrentWeather(cityName);
        // System.out.println("RESPONSE API CURRENT: " + response);

        // Extraemos lati y lon de response
        double lat = response.getCoord().getLat();
        double lon = response.getCoord().getLon();
        // System.out.println("RESPONSE LONGITUDE: " + response.getCoord().getLon());

        // Registrar auditoría
        auditoriaLogService.getCurrentWeather(
            principal.getName(),
            cityName,
            lat,
            lon,
            "GET /current/"
        );
        return response;
    }
    
    //? Obtener el pronóstico de 5 días por latitud y longitud
    @GetMapping("/forecast")
    public ForecasteResponseDto getFiveDayForecast(@RequestParam double lat, @RequestParam double lon, Principal principal) {
        // logica para obtener clima de 5 dias 
        ForecasteResponseDto response = weatherService.getFiveDayForecast(lat, lon);
        // System.out.println("RESPONSE FORESCAST: " + response.getCity().getName());
        String cityName = response.getCity().getName();

        // regustrar audoria
        auditoriaLogService.getCurrentWeather(
            principal.getName(),
            cityName,
            lat,
            lon,
            "GET /forecast"
        );

        return response;
    }
    

    //? Obtener datos de contaminación del aire actual por latitud y longitud
    @GetMapping("/air-pollution")
    public AirPollutionResponseDto getAirPollutionData(@RequestParam double lat, @RequestParam double lon, Principal principal) {

        // obtenemos datos de contaminacion por aire
        AirPollutionResponseDto response = weatherService.getAirPollutionData(lat, lon);
        // System.out.println("RESPONSE AIR_POLLUTION: " + response);

        // para acceder al aqi (indice de contaminacion por aire)
        int aqi = response.getList().get(0).getMain().getAqi();  

        // Registrar auditoría
        auditoriaLogService.getAirPollutionData(            
            principal.getName(),
            lat,
            lon,
            aqi,
            "GET /air-pollution"
        );

        return response;
    }
}
