package com.courses.rhproject.core.config;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "customStatus")
public class CustomStatusEndpoint {

    @ReadOperation
    public Map<String, Object> status() {
        return Map.of(
                "application", "RH Project",
                "status", "Running",
                "uptime", System.currentTimeMillis()
        );
    }
}
