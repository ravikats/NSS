// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.common.functions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class DataEncryption
{
    @Autowired
    Environment env;
    private String key;
    private String salt;
    private String algorithm;
    
    public DataEncryption() {
        this.key = "EmpayKey";
        this.salt = "Empay";
        this.algorithm = "AES/CBC/PKCS5Padding";
    }
}
