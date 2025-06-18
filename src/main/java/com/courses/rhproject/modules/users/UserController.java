package com.courses.rhproject.modules.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints for managing the users")
public class UserController {
    private final UserService userService;

    @Operation(summary = "get all jobs offers", description = "get all jobs offers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "get all job offers"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    ResponseEntity<List<UserResponse>> getAllJobOffers() {
        List<UserResponse> offers = userService.getAllUsers();
        return ResponseEntity.status(201).body(offers);
    }

    @Operation(summary = "Create a Job Offers", description = "Creates a new job offers entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "get all job offers"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    ResponseEntity<UserResponse> createJobOffers(@RequestBody CreateUserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.status(201).body(userResponse);
    }
}
