package com.courses.rhproject.modules.applicants;

import java.util.UUID;

public record CreateApplicant(
        ApplicantStatus status,
        UUID jobOfferId){
}
