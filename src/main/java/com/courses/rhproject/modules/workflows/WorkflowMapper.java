package com.courses.rhproject.modules.workflows;

import com.courses.rhproject.modules.step.StepMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkflowMapper {

    @Mapping(source = "workflowId", target = "workflowId")
    WorkflowResponse toDto(WorkflowEntity workflow);

    @Mapping(target = "workflowId", ignore = true)
    @Mapping(target = "jobOffers", ignore = true)
    WorkflowEntity toEntity(CreateWorkflow workflow);
}