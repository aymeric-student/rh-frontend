package com.courses.rhproject.modules.enterprises;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnterpriseMapper {
    EnterpriseResponse toDto(Enterprise enterprise);
    Enterprise toEntity(CreateEnterprise enterprise);
}
