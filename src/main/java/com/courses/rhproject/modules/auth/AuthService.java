package com.courses.rhproject.modules.auth;

import com.courses.rhproject.core.errors.BusinessException;
import com.courses.rhproject.modules.users.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new BusinessException(UserError.USER_ALREADY_EXISTS);
        }

        UserEntity user = UserEntity.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .firstName(request.firstName())
                .lastName(request.lastName())
                .role(request.role())
                .build();

        userRepository.save(user);
    }

    public UserEntity login(LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException(UserError.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BusinessException(UserError.INVALID_CREDENTIALS);
        }

        return user;
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(UserError.USER_NOT_FOUND));
    }
}
