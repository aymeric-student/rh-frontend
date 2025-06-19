package com.courses.rhproject.modules.users;

import com.courses.rhproject.core.errors.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(CreateUserRequest userRequest) {
        UserEntity user = userMapper.toEntity(userRequest);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public List<UserResponse> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new BusinessException(UserError.USER_NOT_FOUND);
        }

        return users.stream()
                .map(userMapper::toDto)
                .toList();

    }
}
