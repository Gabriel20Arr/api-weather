package com.apiclima.apiclima.services;

import com.apiclima.apiclima.entity.AuditoriaLog;
import com.apiclima.apiclima.repository.AuditLogRepository;
import com.apiclima.apiclima.security.entity.Usuario;
import com.apiclima.apiclima.security.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private UsuarioService usuarioService;

    // Método para guardar auditoría del clima actual
    public AuditoriaLog getCurrentWeather(String nombreUsuario, String nameCity, double lat, double lon, String method) {
        Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).orElseThrow();
        
        AuditoriaLog log = new AuditoriaLog();
        log.setUsuario(usuario);
        log.setNombreUsuario(nombreUsuario);
        log.setNameCity(nameCity);
        log.setLat(lat);
        log.setLon(lon);
        log.setMethod(method);
        log.setTimestamp(LocalDateTime.now());

        return auditLogRepository.save(log);
    }

    // Método para guardar auditoría de contaminación del aire
    public AuditoriaLog getAirPollutionData(String nombreUsuario, double lat, double lon, int aqi, String method) {
        Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).orElseThrow();
        
        AuditoriaLog log = new AuditoriaLog();
        log.setUsuario(usuario);
        log.setNombreUsuario(nombreUsuario);
        log.setLat(lat);
        log.setLon(lon);
        log.setAqi(aqi);
        log.setMethod(method);
        log.setTimestamp(LocalDateTime.now());

        return auditLogRepository.save(log);
    }

    // Obtener las auditorías del usuario
    public List<AuditoriaLog> getAuditoriasByUsuario(int usuarioId) {
        return auditLogRepository.findByUsuarioId(usuarioId);
    }
}