package com.courses.rhproject.modules.workflows;

import com.courses.rhproject.modules.step.StepMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = StepMapper.class)
public interface WorkflowMapper {
    WorkflowResponse toDto(WorkflowEntity workflow);
    WorkflowEntity toEntity(CreateWorkflow workflow);
}
