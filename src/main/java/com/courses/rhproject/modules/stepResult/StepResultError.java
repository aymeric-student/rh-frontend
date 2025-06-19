package com.courses.rhproject.modules.stepResult;

import com.courses.rhproject.core.errors.BaseError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StepResultError implements BaseError {

    STEP_RESULT_ALREADY_EXISTS("Step result already exists for this applicant and step", HttpStatus.CONFLICT),
    STEP_RESULT_NOT_FOUND("Step result not found for this applicant and step", HttpStatus.NOT_FOUND),
    APPLICANT_NOT_FOUND("Applicant not found", HttpStatus.NOT_FOUND),
    PREVIOUS_STEPS_NOT_SUCCESSFUL("Previous steps must be marked as SUCCESS before continuing", HttpStatus.BAD_REQUEST),
    STEP_NOT_FOUND("Step not found", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus httpStatus;
}
