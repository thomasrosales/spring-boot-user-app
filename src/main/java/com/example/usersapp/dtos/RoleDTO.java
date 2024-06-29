package com.example.usersapp.dtos;

import com.example.usersapp.entities.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RoleDTO {

    @NotBlank(message = "The name is required")
    private String name;

    public Role toRole(){
        Role role = new Role();
        role.setName(name.toLowerCase());
        return role;
    }
}
