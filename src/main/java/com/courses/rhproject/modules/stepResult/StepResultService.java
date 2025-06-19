package com.courses.rhproject.modules.stepResult;

import com.courses.rhproject.core.errors.BusinessException;
import com.courses.rhproject.modules.applicants.ApplicantEntity;
import com.courses.rhproject.modules.applicants.ApplicantRepository;
import com.courses.rhproject.modules.step.StepEntity;
import com.courses.rhproject.modules.step.StepRepository;
import com.courses.rhproject.modules.step.StepStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StepResultService {

    private final StepResultRepository stepResultRepository;
    private final ApplicantRepository applicantRepository;
    private final StepRepository stepRepository;
    private final StepResultMapper stepResultMapper;

    public StepResultResponse createStepResult(UUID applicantId, UUID stepId, StepResultUpdateRequest dto) {
        boolean alreadyExists = stepResultRepository
                .existsByApplicant_ApplicantIdAndStep_StepId(applicantId, stepId);

        if (alreadyExists) {
            throw new BusinessException(StepResultError.STEP_RESULT_ALREADY_EXISTS);
        }

        ApplicantEntity applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new BusinessException(StepResultError.APPLICANT_NOT_FOUND));

        StepEntity step = stepRepository.findById(stepId)
                .orElseThrow(() -> new BusinessException(StepResultError.STEP_NOT_FOUND));

        StepResultEntity newResult = stepResultMapper.toEntity(dto, applicant, step);
        StepResultEntity saved = stepResultRepository.save(newResult);

        return stepResultMapper.toResponse(saved);
    }

    public void updateStepResult(UUID applicantId, UUID stepId, StepResultUpdateRequest dto) {
        StepResultEntity existingResult = stepResultRepository
                .findByApplicant_ApplicantIdAndStep_StepId(applicantId, stepId)
                .orElseThrow(() -> new BusinessException(StepResultError.STEP_RESULT_NOT_FOUND));

        stepResultMapper.updateStepResultFromDto(dto, existingResult);
        stepResultRepository.save(existingResult);
    }

    public List<StepResultResponse> getAllByApplicant(UUID applicantId) {
        return stepResultRepository.findAllByApplicant_ApplicantId(applicantId).stream()
                .map(stepResultMapper::toResponse)
                .toList();
    }

}
