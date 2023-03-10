package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Customer Api", version = "v1"))
public class CaseAPaloozaProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseAPaloozaProductServiceApplication.class, args);
		System.out.println("prueba jenkins pipeline");
	}

}
