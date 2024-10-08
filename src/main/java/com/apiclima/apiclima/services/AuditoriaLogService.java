package com.apiclima.apiclima.services;

import com.apiclima.apiclima.entity.AuditoriaLog;
import com.apiclima.apiclima.repository.AuditLogRepository;
import com.apiclima.apiclima.security.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    // Método para guardar un nuevo registro de auditoría
    public AuditoriaLog registrarAuditoria(int usuarioId, String nameCity, String method) {
        AuditoriaLog log = new AuditoriaLog();
        log.setId(usuarioId);;
        log.setNameCity(nameCity);
        log.setMethod(method);
        log.setTimestamp(LocalDateTime.now());

        return auditLogRepository.save(log);
    }

    // Obtener las auditorías del usuario
    public List<AuditoriaLog> getAuditoriasByUsuario(int usuarioId) {
        return auditLogRepository.findByUsuarioId(usuarioId);
    }
}