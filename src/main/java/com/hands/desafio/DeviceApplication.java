package com.hands.desafio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Slf4j
public class DeviceApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(DeviceApplication.class);
        Environment env = app.run(args).getEnvironment();

        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"));
    }
}
