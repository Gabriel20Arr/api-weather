package com.apiclima.apiclima.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import com.apiclima.apiclima.security.entity.Usuario;

@Entity
@Table(name = "auditoriaLog")
@Data
public class AuditoriaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Relación con la tabla usuarios
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;  // Relación con la entidad Usuario

    private String nameCity;
    private String method;
    private LocalDateTime timestamp;


    @Override
    public String toString() {
        return "AuditoriaLog [id=" + id + ", nameCity=" + nameCity + ", method=" + method
                + ", timestamp=" + timestamp + "]";
    }

}

