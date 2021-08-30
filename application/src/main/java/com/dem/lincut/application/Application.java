package com.dem.lincut.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dem.lincut")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
