package com.courses.rhproject.modules.enterprises;

import com.courses.rhproject.core.errors.BaseError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EnterprisesErrors implements BaseError {

    ENTERPRISES_NOT_FOUND("Enterprise not found", HttpStatus.NOT_FOUND),
    ENTERPRISE_HAS_NOT_JOB_OFFER("Enterprise hasn't job offer yet", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus httpStatus;
}
