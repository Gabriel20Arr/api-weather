package com.apiclima.apiclima.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiclima.apiclima.entity.AuditoriaLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditoriaLog, Integer>{
    // Método para obtener las auditorías por usuario
    List<AuditoriaLog> findByUsuarioId(int usuarioId);
}
