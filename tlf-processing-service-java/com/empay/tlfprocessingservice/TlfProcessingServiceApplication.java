// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.tlfprocessingservice;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.empay.*" })
@EntityScan(basePackages = { "com.empay.*" })
@EnableJpaRepositories(basePackages = { "com.empay.*" })
public class TlfProcessingServiceApplication
{
    public static void main(final String[] args) {
        SpringApplication.run((Class)TlfProcessingServiceApplication.class, args);
    }
    
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
