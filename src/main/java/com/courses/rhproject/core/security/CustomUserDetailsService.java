package com.courses.rhproject.core.security;

import com.courses.rhproject.core.errors.BusinessException;
import com.courses.rhproject.modules.users.UserEntity;
import com.courses.rhproject.modules.users.UserError;
import com.courses.rhproject.modules.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessException(UserError.USER_NOT_FOUND));

        return new User(
                user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
