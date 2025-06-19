package com.courses.rhproject.modules.auth;

import com.courses.rhproject.modules.users.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user registration and login")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a new user", description = "Allows a new user (candidate or recruiter) to register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid request or email already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Login a user", description = "Allows an existing user to log in with email and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully logged in"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody LoginRequest request) {
        UserEntity user = authService.login(request);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Get current authenticated user", description = "Returns the authenticated user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authenticated user returned"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - user not authenticated")
    })
    @GetMapping("/me")
    public ResponseEntity<UserEntity> getMe(@AuthenticationPrincipal org.springframework.security.core.userdetails.User principal) {
        String email = principal.getUsername();
        UserEntity user = authService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
