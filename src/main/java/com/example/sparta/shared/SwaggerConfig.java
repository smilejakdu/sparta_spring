package com.example.sparta.shared;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    public static final String USER = "USER";
    public static final String PRODUCT ="PRODUCT";
    public static final String REVIEW = "REVIEW";
    public static final String REPLY = "REPLY";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.sparta"))
                .build()
                .tags(
                        new Tag(USER, String.format("%s %s", USER, " Apis")),
                        new Tag(PRODUCT, String.format("%s %s",PRODUCT, " Apis")),
                        new Tag(REVIEW, String.format("%s %s",REVIEW, " Apis"))
                )
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("mento Swagger")
                .description("Swagger Docs")
                .contact(new Contact("Your Name", "Your Website", "Your Email"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0")
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
       return List.of(new ApiKey("jwt", "Authorization", "header"));
    }
}
