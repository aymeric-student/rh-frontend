package com.courses.rhproject.modules.stepResult;

import com.courses.rhproject.core.errors.BusinessException;
import com.courses.rhproject.modules.applicants.ApplicantRepository;
import com.courses.rhproject.modules.step.StepEntity;
import com.courses.rhproject.modules.step.StepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StepResultService {

    private final StepResultRepository stepResultRepository;
    private final ApplicantRepository applicantRepository;
    private final StepRepository stepRepository;
    private final StepResultMapper stepResultMapper;

    public StepResultResponse createStepResult(UUID applicantId, UUID stepId, StepResultUpdateRequest request) {

        if (stepResultRepository.existsByApplicantIdAndStepId(applicantId, stepId)) {
            throw new BusinessException(StepResultError.STEP_RESULT_ALREADY_EXISTS);
        }

        if (!applicantRepository.existsById(applicantId)) {
            throw new BusinessException(StepResultError.APPLICANT_NOT_FOUND);
        }

        // ✅ Validation simple avec existsById - pas de fetch inutile
        if (!stepRepository.existsById(stepId)) {
            throw new BusinessException(StepResultError.STEP_NOT_FOUND);
        }

        // ✅ Création ultra-simple en une ligne
        StepResultEntity newResult = stepResultMapper.toEntity(request, applicantId, stepId);

        StepResultEntity saved = stepResultRepository.save(newResult);
        return stepResultMapper.toResponse(saved);
    }

    public void updateStepResult(UUID applicantId, UUID stepId, StepResultUpdateRequest request) {
        StepResultEntity existingResult = stepResultRepository
                .findByApplicantIdAndStepId(applicantId, stepId)
                .orElseThrow(() -> new BusinessException(StepResultError.STEP_RESULT_NOT_FOUND));

        existingResult.setStatus(request.status());
        existingResult.setReviewer(request.reviewer());
        existingResult.setComment(request.comment());
        existingResult.setCompletedAt(LocalDateTime.now());

        stepResultRepository.save(existingResult);
    }

    public List<StepResultResponse> getAllByApplicant(UUID applicantId) {
        return stepResultRepository.findAllByApplicantId(applicantId).stream()
                .map(stepResultMapper::toResponse)
                .toList();
    }
}