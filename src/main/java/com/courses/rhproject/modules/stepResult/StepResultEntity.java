package com.courses.rhproject.modules.stepResult;

import com.courses.rhproject.modules.applicants.ApplicantEntity;
import com.courses.rhproject.modules.step.StepEntity;
import com.courses.rhproject.modules.step.StepStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "step_result")
public class StepResultEntity {

    @Id
    @GeneratedValue
    private UUID stepResultId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicant_id", nullable = false)
    private ApplicantEntity applicant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "step_id", nullable = false)
    private StepEntity step;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StepStatus status;

    private String comment;

    private String reviewer;

    private LocalDateTime completedAt;

    public UUID getStepResultId() {
        return stepResultId;
    }

    public void setStepResultId(UUID stepResultId) {
        this.stepResultId = stepResultId;
    }

    public ApplicantEntity getApplicant() {
        return applicant;
    }

    public void setApplicant(ApplicantEntity applicant) {
        this.applicant = applicant;
    }

    public StepEntity getStep() {
        return step;
    }

    public void setStep(StepEntity step) {
        this.step = step;
    }

    public StepStatus getStatus() {
        return status;
    }

    public void setStatus(StepStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
