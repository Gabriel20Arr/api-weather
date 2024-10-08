package com.apiclima.apiclima.security.dto;

import java.util.Set;

import com.apiclima.apiclima.security.enums.RolNombre;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoUsuario {

    @NotBlank
    private String nombre;

    @NotBlank
    private String nombreUsuario;

    @Email
    private String email;

    @NotBlank
    private String password;
    
    private Set<RolNombre> roles;
}
