package com.example.usersapp.controllers;

import com.example.usersapp.dtos.RoleDTO;
import com.example.usersapp.entities.Role;
import com.example.usersapp.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private  RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> roles() {
        return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@Valid  @RequestBody RoleDTO roleDto) {
        Role newRole = roleService.createRole(roleDto.toRole());
        if (newRole == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }
}
