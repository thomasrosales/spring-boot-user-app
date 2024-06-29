package com.example.usersapp.services;

import com.example.usersapp.entities.Role;
import com.example.usersapp.entities.User;
import com.example.usersapp.entities.UserRole;
import com.example.usersapp.repositories.UserRepository;
import com.example.usersapp.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRole createUserRole(User user, Role role) {
        if (userRoleRepository.findByUserAndRole(user, role) != null) {
            return null;
        }
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        return userRoleRepository.save(userRole);

    }
}
