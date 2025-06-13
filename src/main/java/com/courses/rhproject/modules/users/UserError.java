package com.courses.rhproject.modules.users;

import com.courses.rhproject.core.errors.BaseError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum UserError implements BaseError {

    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
    USER_NEED_TO_BE_RECRUTER("User need to be a recruiter", HttpStatus.BAD_REQUEST),
    INVALID_CREDENTIALS("Invalid Credential", HttpStatus.UNAUTHORIZED),
    USER_ALREADY_EXISTS("User already exists", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;
}
