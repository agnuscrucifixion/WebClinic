package com.padwicki.webclinic.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.padwicki.webclinic.domain.entity")
@EnableJpaRepositories("com.padwicki.webclinic.domain.repository")
@ComponentScan("com.padwicki.webclinic")
@SpringBootApplication
public class WebClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebClinicApplication.class, args);
    }

}
