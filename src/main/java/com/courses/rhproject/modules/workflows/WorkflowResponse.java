package com.courses.rhproject.modules.workflows;

import com.courses.rhproject.modules.step.StepResponse;

import java.util.List;
import java.util.UUID;

public record WorkflowResponse(
        UUID workflowId,
        String name,
        String description,
        boolean active,
        List<StepResponse> steps
) {
}
