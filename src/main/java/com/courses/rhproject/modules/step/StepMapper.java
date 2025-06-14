package com.courses.rhproject.modules.step;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StepMapper {
    StepResponse toDto(StepEntity step);
    StepEntity toEntity(CreateStep step);
}
