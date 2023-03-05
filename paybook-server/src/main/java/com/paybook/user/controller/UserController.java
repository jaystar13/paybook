package com.paybook.user.controller;

import com.paybook.security.CurrentUser;
import com.paybook.security.UserPrincipal;
import com.paybook.user.payload.UserSummary;
import com.paybook.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/checkUsername")
    public ResponseEntity<Void> checkUsername(@RequestParam(value = "username") String username) {
        userService.checkUsername(username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<Void> checkEmail(@RequestParam(value = "email") String email) {
        userService.checkEmail(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }
}
