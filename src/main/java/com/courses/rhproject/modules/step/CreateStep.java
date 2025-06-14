package com.courses.rhproject.modules.step;

public record CreateStep(
        String name,
        Integer position,
        Integer durationMinutes,
        String reviewer) {
}
