package com.courses.rhproject.modules.stepResult;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StepResultRepository extends JpaRepository<StepResultEntity, UUID> {

    Optional<StepResultEntity> findByApplicant_ApplicantIdAndStep_StepId(UUID applicantId, UUID stepId);

    boolean existsByApplicant_ApplicantIdAndStep_StepId(UUID applicantId, UUID stepId);

    List<StepResultEntity> findAllByApplicant_ApplicantId(UUID applicantId);
}
