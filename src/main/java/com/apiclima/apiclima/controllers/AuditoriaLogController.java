package com.apiclima.apiclima.controllers;

import com.apiclima.apiclima.entity.AuditoriaLog;
import com.apiclima.apiclima.security.entity.Usuario;
import com.apiclima.apiclima.security.service.UsuarioService;
import com.apiclima.apiclima.services.AuditoriaLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
public class AuditoriaLogController {

    @Autowired
    private AuditoriaLogService auditoriaLogService;

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para que el usuario vea sus propias consultas
    @GetMapping
    public List<AuditoriaLog> getConsultasUsuario(Principal principal) {
        // Obtener el usuario actual
        Usuario usuario = usuarioService.getByNombreUsuario(principal.getName()).orElseThrow(); 
        return auditoriaLogService.getAuditoriasByUsuario(usuario.getId());
    }
}