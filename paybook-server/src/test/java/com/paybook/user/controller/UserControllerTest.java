package com.paybook.user.controller;

import com.paybook.common.documentation.DocumentationWithSecurity;
import com.paybook.common.dto.WithMockCustomUser;
import com.paybook.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest extends DocumentationWithSecurity {
    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentationContextProvider) {
        super.setUp(webApplicationContext, restDocumentationContextProvider);

        MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @DisplayName("현재 사용자를 조회한다.")
    @WithMockCustomUser
    @Test
    void getCurrentUser() throws Exception {
        mockMvc.perform(get("/api/user/me")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("authorization", "Bearer ADMIN_TOKEN")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}