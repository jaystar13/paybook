package com.paybook.user.service;

import com.paybook.auth.payload.SignUpRequest;
import com.paybook.common.exception.AuthException;
import com.paybook.common.exception.ErrorCode;
import com.paybook.user.domain.Role;
import com.paybook.user.domain.RoleName;
import com.paybook.user.domain.User;
import com.paybook.user.repository.RoleRepository;
import com.paybook.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, roleRepository);
    }

    @DisplayName("회원가입을 성공한다.")
    @Test
    void registerUser() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("test one", "test", "test1@test.com", "password1");

        Role role = new Role(RoleName.ROLE_USER);
        User user = signUpRequest.toUserWithRoles(Collections.singleton(role));

        // when
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(roleRepository.findByName(any())).thenReturn(Optional.of(role));
        when(userRepository.save(any())).thenReturn(user);

        // then
        assertThat(userService.registerUser(signUpRequest)).isEqualTo(user.getId());
    }

    @DisplayName("이메일이 존재할 경우 예외를 발생한다.")
    @Test
    void registerUserWithDuplicatedEmail() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("test one", "test", "test1@test.com", "password1");

        // when
        when(userRepository.existsByEmail(any())).thenReturn(true);

        // then
        assertThatThrownBy(() -> userService.registerUser(signUpRequest))
                .isInstanceOf(AuthException.class)
                .hasMessage(ErrorCode.DUPLICATED_EMAIL.getMessage());
    }

    @DisplayName("유저명이 존재할 경우 예외를 발생한다.")
    @Test
    void registerUserWithDuplicatedUsername() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("test one", "test", "test1@test.com", "password1");

        // when
        when(userRepository.existsByUsername(any())).thenReturn(true);

        // then
        assertThatThrownBy(() -> userService.registerUser(signUpRequest))
                .isInstanceOf(AuthException.class)
                .hasMessage(ErrorCode.DUPLICATED_USERNAME.getMessage());
    }

    @DisplayName("유저권한이 세팅되어 있지 않을 경우 예외를 발생한다.")
    @Test
    void registerUserWithUserRoleNotSet() {
        // given
        SignUpRequest signUpRequest = new SignUpRequest("test one", "test", "test1@test.com", "password1");

        // when
        when(roleRepository.findByName(any())).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> userService.registerUser(signUpRequest))
                .isInstanceOf(AuthException.class)
                .hasMessage(ErrorCode.USER_ROLE_NOT_SET.getMessage());
    }
}
