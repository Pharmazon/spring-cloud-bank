package ru.shcheglov.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CardService {
    public static void main(String[] args) {
        SpringApplication.run(CardService.class, args);
    }
}
