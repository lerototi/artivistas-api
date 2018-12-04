package com.artivistas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.artivistas.config.property.ArtivistasApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(ArtivistasApiProperty.class)
public class ArtivistasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtivistasApiApplication.class, args);
	}
}
