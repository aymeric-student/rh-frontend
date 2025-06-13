package com.courses.rhproject.core.errors;

import org.springframework.http.HttpStatus;

public interface BaseError {
    HttpStatus getHttpStatus();
    String getMessage();
}
