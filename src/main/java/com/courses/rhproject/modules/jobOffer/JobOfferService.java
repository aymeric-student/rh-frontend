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
import java.util.stream.Collectors;

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
        if (createJobOfferRequest.enterpriseId() != null) {
            Enterprise enterprise = enterpriseRepository.findById(createJobOfferRequest.enterpriseId())
                    .orElseThrow(() -> new BusinessException(EnterprisesErrors.ENTERPRISES_NOT_FOUND));
            jobOffer.setEnterprise(enterprise);
            log.info("✅ Enterprise linked: {}", enterprise.getName());
        }

        // Lier le workflow
        if (createJobOfferRequest.workflowId() != null) {
            WorkflowEntity workflow = workflowRepository.findById(createJobOfferRequest.workflowId())
                    .orElseThrow(() -> new BusinessException(WorkflowError.WORKFLOW_NOT_FOUND));
            jobOffer.setWorkflow(workflow);
            log.info("✅ Workflow linked: {} (ID: {})", workflow.getName(), workflow.getWorkflowId());
        } else {
            log.warn("⚠️ No workflow ID provided in request");
        }

        // Sauvegarde
        JobOffer saved = jobOfferRepository.save(jobOffer);
        log.info("✅ Job offer saved with ID: {}", saved.getId());

        // Rechargement avec EntityGraph
        JobOffer loaded = jobOfferRepository.findById(saved.getId())
                .orElseThrow(() -> new BusinessException(JobOfferError.JOB_OFFER_NOT_FOUND));

        log.info("✅ Job offer reloaded. Workflow present: {}", loaded.getWorkflow() != null);
        if (loaded.getWorkflow() != null) {
            log.info("✅ Workflow details: {} (ID: {})",
                    loaded.getWorkflow().getName(),
                    loaded.getWorkflow().getWorkflowId());
        }

        JobOfferResponse response = jobOfferMapper.toDto(loaded);
        log.info("✅ Mapped response. Workflow in response: {}", response.workflow() != null);

        return response;
    }

    @Transactional(readOnly = true)
    public List<JobOfferResponse> getAllJobOffers() {
        log.info("🔍 Getting all job offers...");

        // D'abord, essayons la méthode simple
        List<JobOffer> jobOffers = jobOfferRepository.findAll();
        log.info("📊 Found {} job offers", jobOffers.size());

        if (jobOffers.isEmpty()) {
            throw new BusinessException(JobOfferError.JOB_OFFER_NOT_FOUND);
        }

        // Parcourons chaque offre pour débugger
        for (int i = 0; i < jobOffers.size(); i++) {
            JobOffer offer = jobOffers.get(i);
            log.info("🔍 Job Offer {}: ID={}, Title={}", i+1, offer.getId(), offer.getTitle());

            // Vérification du workflow
            if (offer.getWorkflow() == null) {
                log.warn("⚠️ Job Offer {} has NO workflow", offer.getId());
            } else {
                try {
                    // Force le chargement lazy
                    WorkflowEntity workflow = offer.getWorkflow();
                    String workflowName = workflow.getName(); // Ceci va déclencher le chargement
                    log.info("✅ Job Offer {} has workflow: {} (ID: {})",
                            offer.getId(), workflowName, workflow.getWorkflowId());
                } catch (Exception e) {
                    log.error("❌ Error accessing workflow for job offer {}: {}", offer.getId(), e.getMessage());
                }
            }
        }

        // Mapping vers DTO
        List<JobOfferResponse> responses = jobOffers.stream()
                .map(jobOffer -> {
                    JobOfferResponse response = jobOfferMapper.toDto(jobOffer);
                    log.info("📤 Mapped job offer {}: workflow in response = {}",
                            jobOffer.getId(), response.workflow() != null);
                    return response;
                })
                .collect(Collectors.toList());

        return responses;
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
            log.info("✅ Workflow updated: {}", workflow.getName());
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