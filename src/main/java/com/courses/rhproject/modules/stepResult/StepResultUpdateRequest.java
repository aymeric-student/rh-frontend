package com.courses.rhproject.modules.stepResult;

import com.courses.rhproject.modules.step.StepStatus;

public record StepResultUpdateRequest(
        StepStatus status,
        String reviewer,
        String comment
) {}
