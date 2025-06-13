package com.courses.rhproject.core.errors;

import java.util.List;

public record ValidationErrorResponse(
        String code,
        String message,
        int status,
        List<FieldError> errors
) {

    public record FieldError(String field, String message) {}
}

