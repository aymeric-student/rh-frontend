package com.courses.rhproject.modules.jobOffer.dtos;

import com.courses.rhproject.modules.jobOffer.ContractType;
import com.courses.rhproject.modules.workflows.WorkflowResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record JobOfferResponse (
        UUID id,
        String title,
        String description,
        ContractType contractType,
        BigDecimal salary,
        String location,
        LocalDate publicationDate,
        LocalDate expirationDate,
        WorkflowResponse workflow
) {
}
