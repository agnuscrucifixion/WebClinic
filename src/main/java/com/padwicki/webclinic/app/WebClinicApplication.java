package com.padwicki.webclinic.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main class where starts Spring Boot application.
 */
@EntityScan("com.padwicki.webclinic.domain.entity")
@EnableJpaRepositories("com.padwicki.webclinic.domain.repository")
@ComponentScan("com.padwicki.webclinic")
@SpringBootApplication
public class WebClinicApplication {

    /**
     * Start point.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(WebClinicApplication.class, args);
    }

}
