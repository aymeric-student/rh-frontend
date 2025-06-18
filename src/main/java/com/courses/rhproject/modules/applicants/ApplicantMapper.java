package com.courses.rhproject.modules.applicants;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicantMapper {
    ApplicantResponse toDto(ApplicantEntity applicant);
    ApplicantEntity toEntity(CreateApplicant applicant);
}
