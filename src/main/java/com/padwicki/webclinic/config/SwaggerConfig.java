package com.padwicki.webclinic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuration class to customize the Swagger.
 */
@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * Create a {@link Docket} object that contains the controllers to be documented and groups them by name.
     * @return returns a Docket object.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.padwicki.webclinic"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiInfoMetaData());
    }

    /**
     * Create an {@link ApiInfo} object, which is user information that can be modified.
     * @return returns an ApiInfo object.
     */
    private ApiInfo apiInfoMetaData() {

        return new ApiInfoBuilder().title("WebClinic")
                .description("API Endpoint of Clinic")
                .version("1.0.0")
                .build();
    }

}

