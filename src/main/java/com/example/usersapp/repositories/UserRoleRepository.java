package com.example.usersapp.repositories;

import com.example.usersapp.entities.Role;
import com.example.usersapp.entities.User;
import com.example.usersapp.entities.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, String> {
    UserRole findByUserAndRole(User user, Role role);
}
