package com.courses.rhproject.modules.auth;

public record LoginRequest(
        String email,
        String password) {
}
