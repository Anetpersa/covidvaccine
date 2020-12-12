package com.amazingwomenstory.covidvaccine.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${covidVaccine.version}")
    private String covidVaccineVersion;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant(Constants.API_PREFIX + "/**")).build().apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(new BasicAuth("xBasic")))
                .securityContexts(Collections.singletonList(xBasicSecurityContext()));
    }

    private SecurityContext xBasicSecurityContext() {
        return SecurityContext.builder().securityReferences(
                Collections.singletonList(new SecurityReference("xBasic", new AuthorizationScope[0]))).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("CovidVaccineApp REST API", "REST API for CovidVaccineApp", covidVaccineVersion, "Terms of service",
                new Contact("Ana Stancic", "", "ana_stancic@hotmail.com"), "License of API",
                "API license URL", Collections.emptyList());
    }
}