package com.apiclima.apiclima.security.reposity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiclima.apiclima.security.entity.Usuario;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByEmail(String email);

}