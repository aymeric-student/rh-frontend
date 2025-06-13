package com.courses.rhproject.modules.applicants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicants")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for application")
public class ApplicantController {

    private final ApplicantService applicantService;

    @Operation(summary = "Create an applicant", description = "Allows an new applicant" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "applicant successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ApplicantResponse> register(@RequestBody CreateApplicant request) {
        ApplicantResponse applicantResponse = applicantService.createApplicant(request);
        return ResponseEntity.status(201).body(applicantResponse);
    }
}
