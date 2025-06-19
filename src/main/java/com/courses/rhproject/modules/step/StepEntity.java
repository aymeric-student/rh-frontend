package com.courses.rhproject.modules.step;

import com.courses.rhproject.modules.workflows.WorkflowEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "steps")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StepEntity {

    @Id
    @GeneratedValue
    private UUID stepId;

    public UUID getStepId() {
        return stepId;
    }

    public void setStepId(UUID stepId) {
        this.stepId = stepId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public void setWorkflow(WorkflowEntity workflow) {
        this.workflow = workflow;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getReviewer() {
        return reviewer;
    }

    public WorkflowEntity getWorkflow() {
        return workflow;
    }

    private String name;

    private int position;

    private int durationMinutes;

    private String reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_id")
    @JsonBackReference(value = "workflow-steps")
    private WorkflowEntity workflow;
}
