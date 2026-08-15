/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.empay.bin.ACLIntegration
 *  jakarta.annotation.PostConstruct
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.springframework.core.env.Environment
 *  org.springframework.stereotype.Component
 */
package com.empay.bin;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ACLIntegration {
    private static final Logger log = LogManager.getLogger(ACLIntegration.class);
    private final Environment env;

    @PostConstruct
    public void sendPermissionFIle() {
        if (this.env.getProperty("ACL_INTEGRATION_FLAG").strip().equals("1")) {
            log.info("----------Permission File----------");
            String permissionFile = this.readFile();
            log.info(permissionFile);
            log.info("----------Permission File----------");
            this.sendRequest(permissionFile);
        } else {
            log.info("ACL_INTEGRATION_FLAG is not enabled");
        }
    }

    public String readFile() {
        String content = null;
        Path path = Paths.get(this.env.getProperty("BIN_PERMISSION_FILE").strip(), new String[0]);
        try {
            content = Files.readString(path);
            return content;
        }
        catch (Exception e) {
            log.error("Error while reading Permission File:", (Throwable)e);
            return null;
        }
    }

    public void sendRequest(String json) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(this.env.getProperty("ACL_URL"))).header("Content-Type", "application/json").header("Authorization", "Bearer " + this.env.getProperty("ACL_USER_APP_ID").strip()).POST(HttpRequest.BodyPublishers.ofString(json)).build();
        try {
            log.info("Sending Permission File");
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Response code: " + response.statusCode());
            log.info("Response body: " + response.body());
        }
        catch (IOException | InterruptedException e) {
            log.error("Sending Permission File Failed", (Throwable)e);
        }
    }

    public ACLIntegration(Environment env) {
        this.env = env;
    }
}

