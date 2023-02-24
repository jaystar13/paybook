package com.paybook.user.service;

import com.paybook.auth.payload.SignUpRequest;
import com.paybook.common.exception.AuthException;
import com.paybook.common.exception.ErrorCode;
import com.paybook.user.domain.Role;
import com.paybook.user.domain.RoleName;
import com.paybook.user.domain.User;
import com.paybook.user.repository.RoleRepository;
import com.paybook.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public Long registerUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new AuthException(ErrorCode.DUPLICATED_USERNAME);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AuthException(ErrorCode.DUPLICATED_EMAIL);
        }

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AuthException(ErrorCode.USER_ROLE_NOT_SET));

        User result = userRepository.save(signUpRequest.toUserWithRoles(Collections.singleton(userRole)));

        return result.getId();
    }
}
