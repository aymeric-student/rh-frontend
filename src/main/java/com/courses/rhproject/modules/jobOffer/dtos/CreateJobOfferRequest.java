package com.courses.rhproject.modules.jobOffer.dtos;

import com.courses.rhproject.modules.jobOffer.ContractType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateJobOfferRequest(
        String title,
        String description,
        ContractType contractType,
        BigDecimal salary,
        String location,
        LocalDate expirationDate,
        UUID enterpriseId) {
}
