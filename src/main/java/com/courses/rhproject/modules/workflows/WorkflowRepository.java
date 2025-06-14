package com.courses.rhproject.modules.workflows;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkflowRepository extends JpaRepository<WorkflowEntity, UUID> {
}
