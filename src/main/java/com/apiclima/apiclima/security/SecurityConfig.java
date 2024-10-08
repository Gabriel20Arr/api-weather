package com.apiclima.apiclima.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.apiclima.apiclima.security.jwt.JwTokenFilter;
import com.apiclima.apiclima.security.jwt.JwtEntryPoint;
import com.apiclima.apiclima.security.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    JwtEntryPoint jwtEntryPoint;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtEntryPoint jwtEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtEntryPoint = jwtEntryPoint;
    }

    @Bean
    public JwTokenFilter jwTokenFilter() {
        return new JwTokenFilter();
    }

    // Encriptación de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuración del AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Configuración de las reglas de seguridad HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF por simplicidad
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/error").permitAll() //? Rutas permitidas sin autenticación
                .requestMatchers("/api/weather/current/**", "/error").authenticated() //! Ambos perfiles pueden accceder
                .requestMatchers("/api/weather/forecast/**").hasRole("ADMIN") //! Solo los ADMIN pueden acceder
                .requestMatchers("/api/weather/air_pollution/**").hasRole("ADMIN") //! Solo los ADMIN pueden acceder
                .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
            )
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(jwtEntryPoint) // Manejo de errores
            )
            .sessionManagement(sess -> sess
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sin sesiones en el servidor
            );
        
        // Agrega el filtro de JWT antes del filtro de autenticación por defecto
        http.addFilterBefore(jwTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    
        return http.build();
    }
    
}
