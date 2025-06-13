package com.courses.rhproject.modules.applicants;

import java.util.UUID;

public record UserSummary(
        UUID id,
        String email,
        String firstName,
        String lastName
) {}
