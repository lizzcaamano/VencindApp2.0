package com.vecindapp.vecindappv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan(basePackages = {"com.vecindapp.entity"})
@EnableJpaRepositories(basePackages = {"com.vecindapp.repository.jpa"})
@ComponentScan(basePackages = {"com.vecindapp.repository.dao, com.vecindapp.service, com.vecindapp"})
public class VecindAppV2Application {

    public static void main(String[] args) {
        SpringApplication.run(VecindAppV2Application.class, args);
    }

}
