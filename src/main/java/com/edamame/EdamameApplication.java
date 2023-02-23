package com.edamame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("com.edamame.*")
@EnableJpaRepositories(basePackages = {"com.edamame.repository"})
@SpringBootApplication
public class EdamameApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdamameApplication.class, args);
    }

}
