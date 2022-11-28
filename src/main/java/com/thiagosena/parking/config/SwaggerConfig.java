package com.thiagosena.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Component
public class SwaggerConfig {

    private static final String BASIC_AUTH = "basicAuth";

    @Bean
    public Docket apiConfigDocs() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.thiagosena.parking"))
                .build()
                .apiInfo(metaData())
                .securityContexts(List.of(actuatorSecurityContext()))
                .securitySchemes(List.of(basicAuthScheme()));
    }

    private SecurityContext actuatorSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(List.of(basicAuthReference()))
                .build();
    }

    private SecurityScheme basicAuthScheme() {
        return new BasicAuth(BASIC_AUTH);
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference(BASIC_AUTH, new AuthorizationScope[0]);
    }

    private List<SecurityScheme> basicScheme() {
        List<SecurityScheme> schemeList = new ArrayList<>();
        schemeList.add(new BasicAuth(BASIC_AUTH));
        return schemeList;
    }

    private ApiKey apiKey() {
        return new ApiKey("apiKey", "Authorization", "header");
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Parking REST API")
                .description("Spring Boot REST API for Parking")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .build();
    }
}