package com.courses.rhproject.modules.enterprises;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
