package com.algaworks.transito;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Transito API", version = "1", description = "API para autos de infrações de transito"))
public class TransitoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransitoApiApplication.class, args);
    }

}
