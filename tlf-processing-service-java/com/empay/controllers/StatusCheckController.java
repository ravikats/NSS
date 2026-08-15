// 
// Decompiled by Procyon v0.6.0
// 

package com.empay.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusCheckController
{
    @GetMapping({ "/" })
    public ResponseEntity<String> checkStatus() {
        return (ResponseEntity<String>)ResponseEntity.ok((Object)"OK");
    }
}
