package ru.shcheglov.cloud;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import zipkin2.server.internal.EnableZipkinServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinServer {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServer.class, args);
    }

//    public static void main(String[] args) {
//        new SpringApplicationBuilder(ZipkinServer.class)
//                .listeners(new RegisterZipkinHealthIndicators())
//                .properties("spring.config.name=zipkin-server").run(args);
//    }
}
