package com.beanions.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.beanions")
public class BeanionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanionsApplication.class, args);
    }

}
