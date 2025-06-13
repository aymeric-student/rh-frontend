package com.courses.rhproject.modules.users;

import jakarta.persistence.*;

import java.util.UUID;

public record CreateUserRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        RoleType role) {
}
