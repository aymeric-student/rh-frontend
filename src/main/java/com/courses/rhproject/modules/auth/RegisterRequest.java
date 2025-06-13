package com.courses.rhproject.modules.auth;

import com.courses.rhproject.modules.users.RoleType;

public record RegisterRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        RoleType role) {
}
