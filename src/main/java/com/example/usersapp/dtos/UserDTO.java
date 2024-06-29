package com.example.usersapp.dtos;

import com.example.usersapp.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// https://medium.com/@tericcabrel/validate-request-body-and-parameter-in-spring-boot-53ca77f97fe9
// https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints

@Setter
@Getter
@Data
public class UserDTO {

    @NotBlank(message = "The username is required")
    private String username;

    @NotBlank(message = "The password is required")
    private String password;

    @Valid
    private ProfileDTO profile;

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (profile != null) {
            user.setProfile(profile.toProfile());
        }
        return user;
    }

}
