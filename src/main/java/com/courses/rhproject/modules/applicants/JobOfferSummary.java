package com.courses.rhproject.modules.applicants;

import java.time.LocalDate;
import java.util.UUID;

public record JobOfferSummary(
        UUID id,
        String title,
        String location,
        LocalDate expirationDate
) {}
