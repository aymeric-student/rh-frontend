package com.courses.rhproject.modules.applicants;

import com.courses.rhproject.modules.jobOffer.JobOffer;
import com.courses.rhproject.modules.jobOffer.dtos.JobOfferResponse;
import com.courses.rhproject.modules.users.User;
import com.courses.rhproject.modules.users.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicantMapper {
    ApplicantResponse toDto(ApplicantEntity applicant);
    ApplicantEntity toEntity(CreateApplicant applicant);
}
