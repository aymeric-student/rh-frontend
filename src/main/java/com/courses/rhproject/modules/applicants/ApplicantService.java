package com.courses.rhproject.modules.applicants;

import com.courses.rhproject.core.errors.BusinessException;
import com.courses.rhproject.modules.jobOffer.JobOffer;
import com.courses.rhproject.modules.jobOffer.JobOfferError;
import com.courses.rhproject.modules.jobOffer.JobOfferRepository;
import com.courses.rhproject.modules.users.UserEntity;
import com.courses.rhproject.modules.users.UserError;
import com.courses.rhproject.modules.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;
    private final JobOfferRepository jobOfferRepository;
    private final UserRepository userRepository;

    public ApplicantResponse createApplicant(CreateApplicant request, String userEmail) {
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(UserError.USER_NOT_FOUND));

        JobOffer offer = jobOfferRepository.findById(request.jobOfferId())
                .orElseThrow(() -> new BusinessException(JobOfferError.JOB_OFFER_NOT_FOUND));

        ApplicantEntity applicantEntity = applicantMapper.toEntity(request);
        applicantEntity.setApplicationDate(LocalDate.now());
        applicantEntity.setUser(user);
        applicantEntity.setJobOffer(offer);

        applicantRepository.save(applicantEntity);
        return applicantMapper.toDto(applicantEntity);
    }

    public List<ApplicantResponse> getAllApplicants(){
        return applicantRepository.findAll().stream()
                .map(applicantMapper::toDto)
                .toList();
    }
}
