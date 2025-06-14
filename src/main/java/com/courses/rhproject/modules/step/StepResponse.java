package com.courses.rhproject.modules.step;

import java.util.UUID;

public record StepResponse(
        UUID stepId,
        String name,
        Integer position,
        Integer durationMinutes,
        String reviewer) {
}
