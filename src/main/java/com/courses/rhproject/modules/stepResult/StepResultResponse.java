package com.courses.rhproject.modules.stepResult;

import com.courses.rhproject.modules.step.StepResponse;
import com.courses.rhproject.modules.step.StepStatus;

import java.time.LocalDateTime;
import java.util.UUID;

// Option 1 : Inclure l'objet Step complet dans la réponse
public record StepResultResponse(
        UUID stepResultId,
        StepResponse step,  // Au lieu de stepId + stepName séparés
        StepStatus status,
        String reviewer,
        String comment,
        LocalDateTime completedAt
) {}

