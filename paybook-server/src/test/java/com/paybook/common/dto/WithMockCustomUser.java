package com.paybook.common.dto;

import com.paybook.user.domain.RoleName;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {
    long id() default 1L;

    String username() default "jaystar";

    String name() default "jay";

    String email() default "test@test.com";

    String password() default "password";

    RoleName[] roles() default {RoleName.ROLE_USER};
}
