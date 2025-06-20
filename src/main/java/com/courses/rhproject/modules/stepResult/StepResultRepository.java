package com.courses.rhproject.modules.stepResult;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StepResultRepository extends JpaRepository<StepResultEntity, UUID> {

    Optional<StepResultEntity> findByApplicantIdAndStepId(UUID applicantId, UUID stepId);

    boolean existsByApplicantIdAndStepId(UUID applicantId, UUID stepId);

    List<StepResultEntity> findAllByApplicantId(UUID applicantId);
}