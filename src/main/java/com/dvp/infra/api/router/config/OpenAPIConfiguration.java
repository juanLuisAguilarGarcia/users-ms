package com.dvp.infra.api.router.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {

        Contact myContact = new Contact();
        myContact.setName("Juan Luis Aguilar");
        myContact.setEmail("juanaguilargarcia20@gmail.com");

        Info information = new Info()
                .title("Clients System API")
                .version("1.0.0")
                .description("This API exposes endpoints to manage client.")
                .contact(myContact);
        return new OpenAPI().info(information);
    }
}
