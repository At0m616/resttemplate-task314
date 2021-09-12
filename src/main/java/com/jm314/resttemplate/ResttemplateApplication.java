package com.jm314.resttemplate;

import com.jm314.resttemplate.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ResttemplateApplication {
    private static final Logger log = LoggerFactory.getLogger(ResttemplateApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ResttemplateApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            User user = restTemplate.getForObject(
                    "http://91.241.64.178:7081/api/users", User.class);
            log.info(user.toString());
        };
    }
}
