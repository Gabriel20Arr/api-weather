package com.apiclima.apiclima.security.reposity;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiclima.apiclima.security.entity.Rol;
import com.apiclima.apiclima.security.enums.RolNombre;



@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);

}
