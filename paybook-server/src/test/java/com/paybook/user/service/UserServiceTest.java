package com.paybook.user.service;

import com.paybook.auth.payload.SignUpRequest;
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
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName("test one");
        signUpRequest.setUsername("test1");
        signUpRequest.setEmail("test1@test.com");
        signUpRequest.setPassword("password1");

        Role role = new Role(RoleName.ROLE_USER);
        User user = signUpRequest.toUserWithRoles(Collections.singleton(role));

        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(roleRepository.findByName(any())).thenReturn(Optional.of(role));
        when(userRepository.save(any())).thenReturn(user);

        assertThat(userService.registerUser(signUpRequest)).isEqualTo(user.getId());
    }
}