package com.ivoyant.customerservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class CustomerserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run( CustomerserviceApplication.class, args );
    }
}
