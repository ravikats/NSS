/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.security.EncryptionUtil
 *  org.jasypt.encryption.StringEncryptor
 *  org.jasypt.encryption.pbe.PooledPBEStringEncryptor
 *  org.jasypt.encryption.pbe.config.PBEConfig
 *  org.jasypt.encryption.pbe.config.SimplePBEConfig
 *  org.jasypt.iv.IvGenerator
 *  org.jasypt.iv.RandomIvGenerator
 *  org.jasypt.salt.RandomSaltGenerator
 *  org.jasypt.salt.SaltGenerator
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 */
package com.empay.security;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.iv.IvGenerator;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.salt.RandomSaltGenerator;
import org.jasypt.salt.SaltGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionUtil {
    @Bean(name={"encryptorBean"})
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimplePBEConfig config = new SimplePBEConfig();
        config.setPassword("empay");
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setIvGenerator((IvGenerator)new RandomIvGenerator());
        config.setSaltGenerator((SaltGenerator)new RandomSaltGenerator());
        encryptor.setConfig((PBEConfig)config);
        encryptor.setStringOutputType("base64");
        return encryptor;
    }
}

