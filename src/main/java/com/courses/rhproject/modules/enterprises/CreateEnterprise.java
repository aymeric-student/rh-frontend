package com.courses.rhproject.modules.enterprises;

public record CreateEnterprise(
        String name,
        String description,
        String website,
        String phoneNumber,
        String siret,
        String industry,
        String headquartersLocation,
        Integer numberOfEmployees,
        Integer foundedYear
    ) {
}
