package com.courses.rhproject.modules.users;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDto(User user);
    User toEntity(CreateUserRequest userRequest);
}
