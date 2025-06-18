package com.courses.rhproject.modules.jobOffer;

import com.courses.rhproject.core.errors.BusinessException;
import com.courses.rhproject.modules.enterprises.Enterprise;
import com.courses.rhproject.modules.enterprises.EnterpriseRepository;
import com.courses.rhproject.modules.enterprises.EnterprisesErrors;
import com.courses.rhproject.modules.jobOffer.dtos.CreateJobOfferRequest;
import com.courses.rhproject.modules.jobOffer.dtos.JobOfferResponse;
import com.courses.rhproject.modules.users.User;
import com.courses.rhproject.modules.users.UserError;
import com.courses.rhproject.modules.users.UserRepository;
import com.courses.rhproject.modules.workflows.WorkflowEntity;
import com.courses.rhproject.modules.workflows.WorkflowError;
import com.courses.rhproject.modules.workflows.WorkflowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final JobOfferMapper jobOfferMapper;
    private final JobOfferRepository jobOfferRepository;
    private final UserRepository userRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final WorkflowRepository workflowRepository;

    public JobOfferResponse createJobOffer(CreateJobOfferRequest createJobOfferRequest, String userEmail) {
        User recruiter = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(UserError.USER_NOT_FOUND));

        if (!recruiter.isRecruiter()) {
            throw new BusinessException(UserError.USER_NEED_TO_BE_RECRUTER);
        }

        JobOffer jobOffer = jobOfferMapper.toEntity(createJobOfferRequest);
        jobOffer.setPublicationDate(LocalDate.now());

        // Lier l’entreprise
        if (createJobOfferRequest.enterpriseId() != null) {
            Enterprise enterprise = enterpriseRepository.findById(createJobOfferRequest.enterpriseId())
                    .orElseThrow(() -> new BusinessException(EnterprisesErrors.ENTERPRISES_NOT_FOUND));
            jobOffer.setEnterprise(enterprise);
        }

        // Lier le workflow
        if (createJobOfferRequest.workflowId() != null) {
            WorkflowEntity workflow = workflowRepository.findById(createJobOfferRequest.workflowId())
                    .orElseThrow(() -> new BusinessException(WorkflowError.WORKFLOW_NOT_FOUND));
            jobOffer.setWorkflow(workflow);
        }

        // Sauvegarde
        JobOffer saved = jobOfferRepository.save(jobOffer);

        // Reload pour charger le workflow (et ses steps)
        JobOffer loaded = jobOfferRepository.findById(saved.getId())
                .orElseThrow(() -> new BusinessException(JobOfferError.JOB_OFFER_NOT_FOUND));

        return jobOfferMapper.toDto(loaded);
    }

    public List<JobOfferResponse> getAllJobOffers() {
        List<JobOffer> jobOffers = jobOfferRepository.findAll();

        if (jobOffers.isEmpty()) {
            throw new BusinessException(JobOfferError.JOB_OFFER_NOT_FOUND);
        }

        return jobOffers.stream()
                .map(jobOfferMapper::toDto)
                .collect(Collectors.toList());
    }


    public JobOfferResponse updateJobOffer(UUID id, CreateJobOfferRequest dto) {
        JobOffer jobOffer = jobOfferRepository.findById(id)
                .orElseThrow(() -> new BusinessException(JobOfferError.JOB_OFFER_NOT_FOUND));

        jobOffer.setTitle(dto.title());
        jobOffer.setDescription(dto.description());
        jobOffer.setContractType(dto.contractType());
        jobOffer.setSalary(dto.salary());
        jobOffer.setLocation(dto.location());
        jobOffer.setExpirationDate(dto.expirationDate());

        jobOfferRepository.save(jobOffer);
        return jobOfferMapper.toDto(jobOffer);
    }

    public void deleteJobOffer(UUID id) {
        JobOffer jobOffer = jobOfferRepository.findById(id)
                .orElseThrow(() -> new BusinessException(JobOfferError.JOB_OFFER_NOT_FOUND));

        jobOfferRepository.delete(jobOffer);
    }
}
