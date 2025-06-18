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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final JobOfferMapper jobOfferMapper;
    private final JobOfferRepository jobOfferRepository;
    private final UserRepository userRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final WorkflowRepository workflowRepository;

    @Transactional
    public JobOfferResponse createJobOffer(CreateJobOfferRequest createJobOfferRequest, String userEmail) {
        User recruiter = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BusinessException(UserError.USER_NOT_FOUND));

        if (!recruiter.isRecruiter()) {
            throw new BusinessException(UserError.USER_NEED_TO_BE_RECRUTER);
        }

        JobOffer jobOffer = jobOfferMapper.toEntity(createJobOfferRequest);
        jobOffer.setPublicationDate(LocalDate.now());

        // Lier l'entreprise
        Enterprise enterprise = enterpriseRepository.findById(createJobOfferRequest.enterpriseId())
                .orElseThrow(() -> new BusinessException(EnterprisesErrors.ENTERPRISES_NOT_FOUND));
        jobOffer.setEnterprise(enterprise);

        // Lier le workflow
        WorkflowEntity workflow = workflowRepository.findById(createJobOfferRequest.workflowId())
                .orElseThrow(() -> new BusinessException(WorkflowError.WORKFLOW_NOT_FOUND));

        jobOffer.setWorkflow(workflow);

        // Sauvegarde
        JobOffer saved = jobOfferRepository.save(jobOffer);
        JobOfferResponse response = jobOfferMapper.toDto(saved);

        return response;
    }

    @Transactional(readOnly = true)
    public List<JobOfferResponse> getAllJobOffers() {

        List<JobOffer> jobOffers = jobOfferRepository.findAllWithDetails();

        if (jobOffers.isEmpty()) {
            throw new BusinessException(JobOfferError.JOB_OFFER_NOT_FOUND);
        }

        return jobOffers.stream()
                .map(jobOfferMapper::toDto)
                .toList();
    }

    @Transactional
    public JobOfferResponse updateJobOffer(UUID id, CreateJobOfferRequest dto) {
        JobOffer jobOffer = jobOfferRepository.findById(id)
                .orElseThrow(() -> new BusinessException(JobOfferError.JOB_OFFER_NOT_FOUND));

        jobOffer.setTitle(dto.title());
        jobOffer.setDescription(dto.description());
        jobOffer.setContractType(dto.contractType());
        jobOffer.setSalary(dto.salary());
        jobOffer.setLocation(dto.location());
        jobOffer.setExpirationDate(dto.expirationDate());

        // Mise à jour du workflow si fourni
        if (dto.workflowId() != null) {
            WorkflowEntity workflow = workflowRepository.findById(dto.workflowId())
                    .orElseThrow(() -> new BusinessException(WorkflowError.WORKFLOW_NOT_FOUND));
            jobOffer.setWorkflow(workflow);
        }

        jobOfferRepository.save(jobOffer);
        return jobOfferMapper.toDto(jobOffer);
    }

    @Transactional
    public void deleteJobOffer(UUID id) {
        JobOffer jobOffer = jobOfferRepository.findById(id)
                .orElseThrow(() -> new BusinessException(JobOfferError.JOB_OFFER_NOT_FOUND));

        jobOfferRepository.delete(jobOffer);
    }
}