package com.courses.rhproject.modules.stepResult;

import com.courses.rhproject.modules.step.StepStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record StepResultResponse(
        UUID stepResultId,
        UUID stepId,
        StepStatus status,
        String reviewer,
        String comment,
        LocalDateTime completedAt
) {}

