// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.security;

import org.springframework.context.annotation.Bean;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.salt.SaltGenerator;
import org.jasypt.salt.RandomSaltGenerator;
import org.jasypt.iv.IvGenerator;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionUtil
{
    @Bean(name = { "encryptorBean" })
    public StringEncryptor stringEncryptor() {
        final PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        final SimplePBEConfig config = new SimplePBEConfig();
        config.setPassword("empay");
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setIvGenerator((IvGenerator)new RandomIvGenerator());
        config.setSaltGenerator((SaltGenerator)new RandomSaltGenerator());
        encryptor.setConfig((PBEConfig)config);
        encryptor.setStringOutputType("base64");
        return (StringEncryptor)encryptor;
    }
}

