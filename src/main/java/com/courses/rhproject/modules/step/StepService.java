package com.courses.rhproject.modules.step;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StepService {

    private final StepRepository stepRepository;
    private final StepMapper stepMapper;

    public StepResponse createStep(CreateStep step) {
        StepEntity stepEntity = stepMapper.toEntity(step);
        stepRepository.save(stepEntity);
        return stepMapper.toDto(stepEntity);
    }

    public List<StepResponse> getAllSteps() {
        return stepRepository.findAll().stream()
                .map(stepMapper::toDto).toList();
    }
}
