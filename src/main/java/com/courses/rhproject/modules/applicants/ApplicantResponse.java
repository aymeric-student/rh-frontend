package com.courses.rhproject.modules.applicants;

import com.courses.rhproject.modules.jobOffer.dtos.JobOfferResponse;
import com.courses.rhproject.modules.users.UserResponse;

import java.time.LocalDate;
import java.util.UUID;

public record ApplicantResponse(
    UUID applicantId,
    ApplicantStatus status,
    JobOfferResponse jobOffer,
    LocalDate applicationDate,
    UserResponse user) {
}
