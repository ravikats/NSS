/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.splitprocessandstaging.SplitProcessAndStagingApplication
 *  com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
 *  org.springframework.boot.SpringApplication
 *  org.springframework.boot.autoconfigure.SpringBootApplication
 *  org.springframework.boot.autoconfigure.domain.EntityScan
 *  org.springframework.context.ConfigurableApplicationContext
 *  org.springframework.context.annotation.Bean
 *  org.springframework.data.jpa.repository.config.EnableJpaRepositories
 *  org.springframework.web.client.RestTemplate
 *  org.springframework.web.filter.CommonsRequestLoggingFilter
 */
package com.empay.splitprocessandstaging;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication(scanBasePackages={"com.empay.*"})
@EnableJpaRepositories(basePackages={"com.empay.*"})
@EntityScan(basePackages={"com.empay.*"})
@EnableEncryptableProperties
public class SplitProcessAndStagingApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SplitProcessAndStagingApplication.class, (String[])args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeHeaders(false);
        filter.setMaxPayloadLength(100000);
        filter.setBeforeMessagePrefix("REQUEST --> : ");
        filter.setAfterMessagePrefix("REQUEST INFO --> : ");
        return filter;
    }
}

