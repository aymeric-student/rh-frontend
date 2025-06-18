package com.courses.rhproject.modules.enterprises;

import com.courses.rhproject.core.errors.BusinessException;
import com.courses.rhproject.modules.jobOffer.JobOffer;
import com.courses.rhproject.modules.jobOffer.JobOfferMapper;
import com.courses.rhproject.modules.jobOffer.dtos.JobOfferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseMapper enterpriseMapper;
    private final JobOfferMapper jobOfferMapper;

    public EnterpriseResponse createEnterprise(CreateEnterprise createEnterprise) {
         Enterprise enterprise = enterpriseMapper.toEntity(createEnterprise);
         enterpriseRepository.save(enterprise);
         return enterpriseMapper.toDto(enterprise);
    }

    public List<EnterpriseResponse> getAllEnterprises() {
        List<EnterpriseResponse> enterprises = enterpriseRepository.findAll().stream()
                .map(enterpriseMapper::toDto)
                .toList();

        if (enterprises.isEmpty()) {
            throw new BusinessException(EnterprisesErrors.ENTERPRISES_NOT_FOUND);
        }

        return enterprises;
    }

    public List<JobOfferResponse> getAllOffersByEnterprise(UUID enterpriseId) {
        Enterprise enterprise = enterpriseRepository.findByIdWithOffersAndWorkflow(enterpriseId)
                .orElseThrow(() -> new BusinessException(EnterprisesErrors.ENTERPRISES_NOT_FOUND));

        if (enterprise.getJobOffers().isEmpty()) {
            throw new BusinessException(EnterprisesErrors.ENTERPRISE_HAS_NOT_JOB_OFFER);
        }

        return enterprise.getJobOffers().stream()
                .map(jobOfferMapper::toDto)
                .toList();
    }
}
