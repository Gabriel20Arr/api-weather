package com.apiclima.apiclima.config;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilter() {
        FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RateLimitFilter());
        // registrationBean.addUrlPatterns("/api/weather/current/**");  // Aplica el filtro solo a estas rutas
        // registrationBean
        //         .addResourceHandler("/swagger-ui/**")
        //         .addResourceLocations("classpath:/META-INF/resources/webjars/")
        //         .setCachePeriod(3600)
        //         .resourceChain(true);
        return registrationBean;
    }
}
