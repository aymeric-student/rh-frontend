package com.courses.rhproject.modules.stepResult;

import com.courses.rhproject.modules.step.StepStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "step_result")
public class StepResultEntity {

    @Id
    @GeneratedValue
    private UUID stepResultId;

    @Column(name = "applicant_id", nullable = false)
    private UUID applicantId;

    @Column(name = "step_id", nullable = false)
    private UUID stepId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StepStatus status;

    private String comment;
    private String reviewer;
    private LocalDateTime completedAt;
}