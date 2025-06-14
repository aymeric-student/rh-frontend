package com.courses.rhproject.modules.enterprises;

import com.courses.rhproject.modules.jobOffer.dtos.JobOfferResponse;

import java.util.List;
import java.util.UUID;

public record EnterpriseResponse(
        UUID enterpriseId,
        String name,
        String description,
        String website,
        String phoneNumber,
        String siret,
        String industry,
        String headquartersLocation,
        Integer numberOfEmployees,
        Integer foundedYear,
        List<JobOfferResponse> jobOffers
) {
}
