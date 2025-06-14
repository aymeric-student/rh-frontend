package com.courses.rhproject.modules.step;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/steps")
@RequiredArgsConstructor
@Tag(name = "Steps", description = "Endpoints for managing recruitment steps")
public class StepController {

    private final StepService stepService;

    @Operation(summary = "Create a new step", description = "Creates a new recruitment step")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Step created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<StepResponse> createStep(@RequestBody CreateStep step) {
        StepResponse created = stepService.createStep(step);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Get all steps", description = "Retrieve all recruitment steps")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of steps retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<StepResponse>> getAllSteps() {
        List<StepResponse> steps = stepService.getAllSteps();
        return ResponseEntity.ok(steps);
    }
}
