package com.courses.rhproject.modules.jobOffer;

import com.courses.rhproject.modules.jobOffer.dtos.CreateJobOfferRequest;
import com.courses.rhproject.modules.jobOffer.dtos.JobOfferResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobOfferMapper {
    JobOfferResponse toDto(JobOffer jobOffer);
    JobOffer toEntity(CreateJobOfferRequest jobOffer);
}

