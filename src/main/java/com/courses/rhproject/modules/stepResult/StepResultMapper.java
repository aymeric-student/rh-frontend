package com.courses.rhproject.modules.stepResult;

import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface StepResultMapper {

    StepResultResponse toResponse(StepResultEntity entity);
    StepResultEntity toEntity(StepResultUpdateRequest request, UUID applicantId, UUID stepId);
}