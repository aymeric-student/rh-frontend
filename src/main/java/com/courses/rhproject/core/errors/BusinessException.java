package com.courses.rhproject.core.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final String code;
    private final HttpStatus httpStatus;

    public BusinessException(BaseError errorCode) {
        super(errorCode.getMessage());
        this.code = ((Enum<?>) errorCode).name();
        this.httpStatus = errorCode.getHttpStatus();
    }
}
