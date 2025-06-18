package com.courses.rhproject.modules.enterprises;

import com.courses.rhproject.modules.jobOffer.dtos.JobOfferResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enterprises")
@RequiredArgsConstructor
@Tag(name = "Enterprises", description = "Endpoints for managing the enterprises")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    @Operation(summary = "get all enterprises", description = "get all enterprises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "get all enterprises"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    ResponseEntity<List<EnterpriseResponse>> getAllJobOffers() {
        List<EnterpriseResponse> enterprises = enterpriseService.getAllEnterprises();
        return ResponseEntity.status(201).body(enterprises);
    }

    @Operation(summary = "Create an Enterprise", description = "Creates a enterprise entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Enterprise created"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<EnterpriseResponse> createJobOffer(
            @RequestBody CreateEnterprise enterprise) {

        EnterpriseResponse response = enterpriseService.createEnterprise(enterprise);
        return ResponseEntity.status(201).body(response);
    }

    @Operation(summary = "Get all job offers by enterprise", description = "Returns all job offers related to a specific enterprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Job offers found"),
            @ApiResponse(responseCode = "404", description = "Enterprise not found or has no job offers"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{enterpriseId}/job-offers")
    public ResponseEntity<List<JobOfferResponse>> getAllJobOffersByEnterprise(
            @PathVariable UUID enterpriseId) {
        List<JobOfferResponse> offers = enterpriseService.getAllOffersByEnterprise(enterpriseId);
        return ResponseEntity.ok(offers);
    }
}
