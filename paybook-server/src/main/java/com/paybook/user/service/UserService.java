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
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public Long registerUser(SignUpRequest signUpRequest) {
        checkUsername(signUpRequest.getUsername());
        checkEmail(signUpRequest.getEmail());

        Role userRole = findRoleByName(RoleName.ROLE_USER);

        User result = userRepository.save(signUpRequest.toUserWithRoles(Collections.singleton(userRole)));

        return result.getId();
    }

    private Role findRoleByName(RoleName roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new AuthException(ErrorCode.USER_ROLE_NOT_SET));
    }

    @Transactional(readOnly = true)
    public void checkUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new AuthException(ErrorCode.DUPLICATED_USERNAME);
        }
    }

    @Transactional(readOnly = true)
    public void checkEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AuthException(ErrorCode.DUPLICATED_EMAIL);
        }
    }
}
