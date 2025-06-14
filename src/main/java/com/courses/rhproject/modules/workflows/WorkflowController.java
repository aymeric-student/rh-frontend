package com.courses.rhproject.modules.workflows;

import com.courses.rhproject.modules.step.CreateStep;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workflows")
@RequiredArgsConstructor
@Tag(name = "Workflows", description = "Endpoints for managing recruitment workflows")
public class WorkflowController {

    private final WorkflowService workflowService;

    @Operation(summary = "Create a new workflow", description = "Creates a new recruitment workflow with its metadata")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workflow created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<WorkflowResponse> createWorkflow(@RequestBody CreateWorkflow createWorkflow) {
        WorkflowResponse created = workflowService.createWorkflow(createWorkflow);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Get all workflows", description = "Retrieves all available recruitment workflows")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workflows retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<WorkflowResponse>> getAllWorkflows() {
        List<WorkflowResponse> workflows = workflowService.getAllWorkflows();
        return ResponseEntity.ok(workflows);
    }

    @PostMapping("/{workflowId}/steps")
    @Operation(summary = "Add a step to a workflow", description = "Adds a new step to the specified workflow and returns the updated workflow")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Step added successfully"),
            @ApiResponse(responseCode = "404", description = "Workflow not found"),
            @ApiResponse(responseCode = "400", description = "Invalid step data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<WorkflowResponse> addStepToWorkflow(
            @PathVariable UUID workflowId,
            @RequestBody CreateStep createStep) {

        WorkflowResponse updatedWorkflow = workflowService.addStepToWorkflow(workflowId, createStep);
        return ResponseEntity.status(201).body(updatedWorkflow);
    }
}
