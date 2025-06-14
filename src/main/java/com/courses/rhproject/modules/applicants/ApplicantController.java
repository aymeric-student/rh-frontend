package com.courses.rhproject.modules.applicants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/applicants")
@RequiredArgsConstructor
@Tag(name = "Applicants", description = "Endpoints for job applications")
public class ApplicantController {

    private final ApplicantService applicantService;

    @Operation(summary = "Create an applicant", description = "Allows creating a new job application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Application successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ApplicantResponse> createApplication(
            @RequestBody CreateApplicant request,
            @AuthenticationPrincipal User principal) {

        String userEmail = principal.getUsername();
        ApplicantResponse applicantResponse = applicantService.createApplicant(request, userEmail);
        return ResponseEntity.status(201).body(applicantResponse);
    }

    @Operation(summary = "get all applicants", description = "get all applicants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "get all applicants"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    ResponseEntity<List<ApplicantResponse>> getAllJobOffers() {
        List<ApplicantResponse> applicants = applicantService.getAllApplicants();
        return ResponseEntity.status(201).body(applicants);
    }
}
