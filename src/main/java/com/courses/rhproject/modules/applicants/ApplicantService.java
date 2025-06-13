package com.courses.rhproject.modules.applicants;

import com.courses.rhproject.core.errors.BusinessException;
import com.courses.rhproject.modules.jobOffer.JobOffer;
import com.courses.rhproject.modules.jobOffer.JobOfferError;
import com.courses.rhproject.modules.jobOffer.JobOfferRepository;
import com.courses.rhproject.modules.users.User;
import com.courses.rhproject.modules.users.UserError;
import com.courses.rhproject.modules.users.UserRepository;
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
    private final UserRepository userRepository;

    public ApplicantResponse createApplicant(CreateApplicant request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(UserError.USER_NOT_FOUND));

        Optional<JobOffer> offer = jobOfferRepository.findById(request.jobOfferId());

        if (offer.isEmpty()) {
            throw new BusinessException(JobOfferError.JOB_OFFER_NOT_FOUND);
        }

        ApplicantEntity applicantEntity = applicantMapper.toEntity(request);
        applicantEntity.setApplicationDate(LocalDate.now());


        applicantRepository.save(applicantEntity);
        return applicantMapper.toDto(applicantEntity);
    }
}
