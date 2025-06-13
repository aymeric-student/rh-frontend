package com.courses.rhproject.modules.users;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        String firstName,
        String lastName,
        RoleType role) {
}
