package com.courses.rhproject.modules.workflows;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkflowMapper {

    WorkflowResponse toDto(WorkflowEntity workflow);
    WorkflowEntity toEntity(CreateWorkflow workflow);
}