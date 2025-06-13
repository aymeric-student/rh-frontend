package com.courses.rhproject.modules.jobOffer;

import com.courses.rhproject.core.errors.BaseError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum JobOfferError implements BaseError {

    JOB_OFFER_NOT_FOUND("Job offer not found", HttpStatus.NOT_FOUND),
    JOB_OFFER_ALREADY_EXISTS("Job offer already exists", HttpStatus.CONFLICT),
    INVALID_JOB_OFFER("Invalid job offer data", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;
}
