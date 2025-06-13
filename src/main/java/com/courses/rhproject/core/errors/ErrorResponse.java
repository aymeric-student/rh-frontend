package com.courses.rhproject.core.errors;

public record ErrorResponse(String code, String message, int status) {
}
