package com.courses.rhproject.modules.applicants;

import com.courses.rhproject.modules.jobOffer.JobOffer;
import com.courses.rhproject.modules.users.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

public record ApplicantResponse(
    UUID applicantId,
    ApplicantStatus status,
    JobOffer jobOffer,
    LocalDate applicationDate,
    User user) {
}
