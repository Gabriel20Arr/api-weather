package com.apiclima.apiclima.config;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RateLimitFilter implements Filter {

    // Almacenamiento en memoria para las solicitudes
    private ConcurrentHashMap<String, RateLimiter> clientRequestCounts = new ConcurrentHashMap<>();

    // Configuración de límite
    private static final int MAX_REQUESTS_PER_MINUTE = 2; // 2 solicitudes por minuto

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Obtener token del usuario
        String clientId = getClientId(req);

        // Obtener el RateLimiter asociado con el cliente o crearlo si no existe
        RateLimiter rateLimiter = clientRequestCounts.computeIfAbsent(clientId, k -> new RateLimiter(MAX_REQUESTS_PER_MINUTE, 60));

        // Verificar si el cliente excedió el límite de solicitudes
        if (rateLimiter.isLimitExceeded()) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // HTTP 429
            res.getWriter().write("Haz alcanzado el maximo de 5 solicitudes por minuto, intente nuevamente en 1 minuto.");
            return;
        }

        // Continuar con la cadena de filtros si el límite no se ha excedido
        chain.doFilter(req, res);
    }

    // Función para obtener la identificación del cliente (Token o IP como respaldo)
    private String getClientId(HttpServletRequest req) {
        // Obtener el token del encabezado Authorization
        String authHeader = req.getHeader("Authorization");
        
        // Verificar si el encabezado Authorization está presente y comienza con "Bearer"
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Extraer el token (remover la parte "Bearer ")
            return authHeader.substring(7);
        } else {
            // Si no hay token, usar la IP como respaldo
            return req.getRemoteAddr();
        }
    }

    // Clase interna que implementa el Rate Limiter
    private static class RateLimiter {
        private final int maxRequests;
        private final long timeWindowInSeconds;
        private long windowStartTime;
        private int requestCount;

        public RateLimiter(int maxRequests, long timeWindowInSeconds) {
            this.maxRequests = maxRequests;
            this.timeWindowInSeconds = timeWindowInSeconds;
            this.windowStartTime = System.currentTimeMillis();
            this.requestCount = 0;
        }

        public synchronized boolean isLimitExceeded() {
            long currentTime = System.currentTimeMillis();

            // Verificar si el periodo de tiempo ha expirado
            if (currentTime - windowStartTime > TimeUnit.SECONDS.toMillis(timeWindowInSeconds)) {
                // Reiniciar la ventana de tiempo
                windowStartTime = currentTime;
                requestCount = 0;
            }

            requestCount++;

            // Verificar si se ha excedido el límite de solicitudes
            return requestCount > maxRequests;
        }
    }
}
