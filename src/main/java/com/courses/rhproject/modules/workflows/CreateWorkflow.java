package com.courses.rhproject.modules.workflows;

public record CreateWorkflow(
        String name,
        String description,
        boolean active) {
}
