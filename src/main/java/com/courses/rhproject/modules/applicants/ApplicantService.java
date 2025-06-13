package com.courses.rhproject.modules.applicants;

import com.courses.rhproject.core.errors.BusinessException;
import com.courses.rhproject.modules.jobOffer.JobOffer;
import com.courses.rhproject.modules.jobOffer.JobOfferError;
import com.courses.rhproject.modules.jobOffer.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;
    private final JobOfferRepository jobOfferRepository;

    public ApplicantResponse createApplicant(CreateApplicant applicant) {
        Optional<JobOffer> offer = jobOfferRepository.findById(applicant.jobOfferId());

        if (offer.isEmpty()) {
            throw new BusinessException(JobOfferError.JOB_OFFER_NOT_FOUND);
        }

        ApplicantEntity applicantEntity = applicantMapper.toEntity(applicant);
        applicantEntity.setApplicationDate(LocalDate.now());


        applicantRepository.save(applicantEntity);
        return applicantMapper.toDto(applicantEntity);
    }
}
