package com.paybook.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paybook.auth.payload.SignUpRequest;
import com.paybook.config.SecurityConfig;
import com.paybook.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        })
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {

    }

    @DisplayName("화원가입을 성공한다.")
    @Test
    void registerUser() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest("test one", "test", "test1@test.com", "password1");

        when(userService.registerUser(any())).thenReturn(1L);

        mockMvc.perform(post("/api/auth/signup")
                        .content(new ObjectMapper().writeValueAsBytes(signUpRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
