package com.courses.rhproject.modules.workflows;

import com.courses.rhproject.core.errors.BaseError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum WorkflowError implements BaseError {
    WORKFLOW_NOT_FOUND("workflow not found", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;
}
