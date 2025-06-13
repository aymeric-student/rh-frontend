package com.courses.rhproject.modules.jobOffer;

import com.courses.rhproject.modules.jobOffer.dtos.*;
import com.courses.rhproject.modules.users.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/job-offers")
@RequiredArgsConstructor
@Tag(name = "JobOffer", description = "Endpoints for managing the jobs offers")
public class JobOfferController {

    private final JobService jobService;

    @Operation(summary = "get all jobs offers", description = "get all jobs offers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "get all job offers"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    ResponseEntity<List<JobOfferResponse>> getAllJobOffers() {
        List<JobOfferResponse> offers = jobService.getAllJobOffers();
        return ResponseEntity.status(201).body(offers);
    }

    @Operation(summary = "Create a Job Offers", description = "Creates a new job offers entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "get all job offers"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<JobOfferResponse> createJobOffer(
            @RequestBody CreateJobOfferRequest request,
            @AuthenticationPrincipal User user) {
        JobOfferResponse response = jobService.createJobOffer(request, user);
        return ResponseEntity.status(201).body(response);
    }


    @Operation(summary = "Update a Job Offer", description = "Update a job offer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Job offer updated"),
            @ApiResponse(responseCode = "404", description = "Job offer not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PutMapping("/{id}")
    public ResponseEntity<JobOfferResponse> updateJobOffer(
            @PathVariable UUID id,
            @RequestBody CreateJobOfferRequest request) {
        JobOfferResponse updated = jobService.updateJobOffer(id, request);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete a Job Offer", description = "Delete a job offer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Job offer deleted"),
            @ApiResponse(responseCode = "404", description = "Job offer not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobOffer(@PathVariable UUID id) {
        jobService.deleteJobOffer(id);
        return ResponseEntity.noContent().build();
    }
}
