package com.jobtest.techmanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Tech Manager", version = "1.0", description = "Tech Manager API"))
public class TechManagerApplication {

    public static void main(String[] args) {

        SpringApplication.run(TechManagerApplication.class, args);
    }

}