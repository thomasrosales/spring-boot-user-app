package com.example.usersapp.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UserRoleDTO {

    @NotBlank(message = "The role is required")
    private String role;
}
