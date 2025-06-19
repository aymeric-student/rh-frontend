package com.courses.rhproject.modules.stepResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/step-results")
@RequiredArgsConstructor
@Tag(name = "Step Results", description = "Manage results of applicant steps")
public class StepResultController {

    private final StepResultService stepResultService;

    @Operation(summary = "Create a step result", description = "Creates a new step result for an applicant")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Step result created"),
            @ApiResponse(responseCode = "400", description = "Invalid data or already exists"),
            @ApiResponse(responseCode = "404", description = "Applicant or Step not found")
    })
    @PostMapping("/applicant/{applicantId}/step/{stepId}")
    public ResponseEntity<StepResultResponse> createStepResult(
            @PathVariable UUID applicantId,
            @PathVariable UUID stepId,
            @RequestBody StepResultUpdateRequest dto
    ) {
        StepResultResponse response = stepResultService.createStepResult(applicantId, stepId, dto);
        return ResponseEntity.status(201).body(response);
    }


    @Operation(summary = "Update a step result", description = "Updates an existing step result for an applicant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Step result updated"),
            @ApiResponse(responseCode = "404", description = "Step result not found")
    })
    @PutMapping("/applicant/{applicantId}/step/{stepId}")
    public ResponseEntity<Void> updateStepResult(
            @PathVariable UUID applicantId,
            @PathVariable UUID stepId,
            @RequestBody StepResultUpdateRequest dto
    ) {
        stepResultService.updateStepResult(applicantId, stepId, dto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "List step results", description = "List all step results for a given applicant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List retrieved"),
            @ApiResponse(responseCode = "404", description = "Applicant not found")
    })
    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<StepResultResponse>> getStepResultsByApplicant(@PathVariable UUID applicantId) {
        return ResponseEntity.ok(stepResultService.getAllByApplicant(applicantId));
    }
}
