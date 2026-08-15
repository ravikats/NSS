package com.empay.irfservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.empay.common",
        "com.empay.irfservice"
})
@EntityScan(basePackages = {
        "com.empay.common.entities",
        "com.empay.irfservice"
})
@EnableJpaRepositories(basePackages = {
        "com.empay.common.repo",
        "com.empay.irfservice"
})
public class IrfServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(IrfServiceApplication.class, args);
    }
}
