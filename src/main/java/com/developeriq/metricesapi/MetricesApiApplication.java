package com.developeriq.metricesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.developeriq.metricesapi")
public class MetricesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricesApiApplication.class, args);
	}

}
