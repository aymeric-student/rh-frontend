package com.courses.rhproject.modules.workflows;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface WorkflowRepository extends JpaRepository<WorkflowEntity, UUID> {

       @Query("""
            SELECT DISTINCT we\s
            FROM WorkflowEntity we
            LEFT JOIN FETCH we.steps
       \s""")
       List<WorkflowEntity> findAllWorkflows();
}
