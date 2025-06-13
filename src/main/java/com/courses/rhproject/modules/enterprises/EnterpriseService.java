package com.courses.rhproject.modules.enterprises;

import com.courses.rhproject.core.errors.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseMapper enterpriseMapper;

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
}
