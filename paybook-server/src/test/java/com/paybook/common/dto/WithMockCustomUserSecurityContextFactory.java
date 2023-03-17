package com.paybook.common.dto;

import com.paybook.security.UserPrincipal;
import com.paybook.user.domain.Role;
import com.paybook.user.domain.RoleName;
import com.paybook.user.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collections;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User user = new User();

        user.setId(customUser.id());
        user.setUsername(customUser.username());
        user.setName(customUser.name());
        user.setEmail(customUser.email());
        user.setPassword(customUser.password());
        user.setRoles(Collections.singleton(new Role(RoleName.ROLE_USER)));

        UserPrincipal principal = UserPrincipal.create(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, user.getPassword(), principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
