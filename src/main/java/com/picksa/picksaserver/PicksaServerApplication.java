package com.picksa.picksaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PicksaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PicksaServerApplication.class, args);
    }

}