package com.paybook.auth.payload;

import com.paybook.user.domain.Role;
import com.paybook.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class SignUpRequest {

    @NotBlank
    @Size(min = 4, max = 40)
    private String name;

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    public User toUserWithRoles(Set<Role> roles) {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.getPassword());
        user.setRoles(roles);

        return user;
    }
}
