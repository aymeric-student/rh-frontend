package com.courses.rhproject.modules.workflows;

import com.courses.rhproject.core.errors.BusinessException;
import com.courses.rhproject.modules.step.CreateStep;
import com.courses.rhproject.modules.step.StepEntity;
import com.courses.rhproject.modules.step.StepMapper;
import com.courses.rhproject.modules.step.StepService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkflowService {

    private final WorkflowRepository workflowRepository;
    private final WorkflowMapper workflowMapper;
    private final StepMapper stepMapper;

    public WorkflowResponse createWorkflow(CreateWorkflow createWorkflow) {
        WorkflowEntity workflow = workflowMapper.toEntity(createWorkflow);
        workflowRepository.save(workflow);
        return workflowMapper.toDto(workflow);
    }

    public List<WorkflowResponse> getAllWorkflows() {
        return workflowRepository.findAllWorkflows().stream()
                .map(workflowMapper::toDto)
                .toList();
    }

    public WorkflowResponse addStepToWorkflow(UUID workflowId, CreateStep createStep) {
        WorkflowEntity workflow = workflowRepository.findById(workflowId)
                .orElseThrow(() -> new BusinessException(WorkflowError.WORKFLOW_NOT_FOUND));

        StepEntity step = stepMapper.toEntity(createStep);

        step.setWorkflow(workflow);
        workflow.getSteps().add(step);

        workflowRepository.save(workflow);
        return workflowMapper.toDto(workflow);
    }

}
