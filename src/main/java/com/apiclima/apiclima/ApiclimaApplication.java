package com.apiclima.apiclima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiclimaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiclimaApplication.class, args);
	}

}
