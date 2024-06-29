package com.example.usersapp.controllers;

//import com.example.usersapp.models.User;

import com.example.usersapp.dtos.UserDTO;
import com.example.usersapp.dtos.UserRoleDTO;
import com.example.usersapp.entities.Role;
import com.example.usersapp.entities.User;
import com.example.usersapp.entities.UserRole;
import com.example.usersapp.services.RoleService;
import com.example.usersapp.services.UserRoleService;
import com.example.usersapp.services.UserService;
import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @GetMapping  //handler method
    @Timed(value = "get.users")
    public ResponseEntity<Page<User>> getAllUsers(
            // @RequestParam(required = false) String username,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "100") Integer size) {
        return ResponseEntity.ok().body(userService.getUsers(page, size));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not found", username));
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
        User newUser = userService.createUser(userDTO.toUser());
        if (newUser == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("User %s already exist", userDTO.getUsername()));
        }
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable String username) {
        User newUser = userService.updateUser(userDTO.toUser(), username);
        if (newUser == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s does not exist", username));
        }
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable String username) {
        User deletedUser = userService.removeUser(username);
        if (deletedUser == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s does not exist", username));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ACTIONS

    @PostMapping("/{username}/assign-role")
    public ResponseEntity<Role> assignRoleToUser(@PathVariable String username, @Valid @RequestBody UserRoleDTO userRoleDTO) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s does not exist", username));
        }
        Role role = roleService.getRoleByName(userRoleDTO.getRole());
        if (role == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role %s not found", userRoleDTO.getRole()));
        }
        UserRole userRole = userRoleService.createUserRole(user, role);
        return new ResponseEntity<>(userRole.getRole(), HttpStatus.CREATED);
    }

    @GetMapping("/{username}/roles")
    public ResponseEntity<List<Role>> getUserRoles(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s does not exist", username));
        }
        List<Role> roles = roleService.getRolesByUserId(user.getId());
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


}
