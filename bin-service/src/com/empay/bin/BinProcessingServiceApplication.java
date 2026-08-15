/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.bin.BinProcessingServiceApplication
 *  org.springframework.boot.SpringApplication
 *  org.springframework.boot.autoconfigure.SpringBootApplication
 *  org.springframework.boot.autoconfigure.domain.EntityScan
 *  org.springframework.context.annotation.Bean
 *  org.springframework.data.jpa.repository.config.EnableJpaRepositories
 *  org.springframework.web.client.RestTemplate
 */
package com.empay.bin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages={"com.empay.*"})
@EntityScan(basePackages={"com.empay.entities"})
@EnableJpaRepositories(basePackages={"com.empay.repositories"})
public class BinProcessingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BinProcessingServiceApplication.class, (String[])args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

