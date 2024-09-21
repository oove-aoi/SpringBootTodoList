package com.example.spring_todolist.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TodoList 簡易示範")
                        .description("SpringBoot 3.X application")
                        .version("v0.0.1")
                        .license(new License().name(" ").url("http://springdoc.org"))
                        .contact(new Contact().name("REN-XU").email("x78172481@gmail.com").url("x78172481@gmail.com"))
                );
    }
}
