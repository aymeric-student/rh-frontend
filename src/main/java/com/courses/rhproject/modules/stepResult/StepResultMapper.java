package com.courses.rhproject.modules.stepResult;

import com.courses.rhproject.modules.applicants.ApplicantEntity;
import com.courses.rhproject.modules.step.StepEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StepResultMapper {
    @Mapping(target = "stepResultId", ignore = true)
    @Mapping(target = "applicant", source = "applicant")
    @Mapping(target = "step", source = "step")
    @Mapping(target = "status", source = "dto.status")
    @Mapping(target = "reviewer", source = "dto.reviewer")
    @Mapping(target = "comment", source = "dto.comment")
    @Mapping(target = "completedAt", expression = "java(java.time.LocalDateTime.now())")
    StepResultEntity toEntity(StepResultUpdateRequest dto, ApplicantEntity applicant, StepEntity step);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "completedAt", expression = "java(java.time.LocalDateTime.now())")
    void updateStepResultFromDto(StepResultUpdateRequest dto, @MappingTarget StepResultEntity entity);

    @Mapping(target = "stepResultId", source = "stepResultId")
    @Mapping(target = "stepId", source = "step.stepId")
    @Mapping(target = "stepName", source = "step.name")
    StepResultResponse toResponse(StepResultEntity entity);
}
