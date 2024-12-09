package com.example.datawarehouseDBwarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.datawarehouseDBwarehouse.Repository")
public class DatawarehouseDBwarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatawarehouseDBwarehouseApplication.class, args);
	}
}
