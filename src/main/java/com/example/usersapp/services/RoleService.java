package com.example.usersapp.services;


import com.example.usersapp.entities.Role;
import com.example.usersapp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public List<Role> getRolesByUserId(String userId){
        return roleRepository.findRolesByUserId(userId);
    }

    public Role getRoleByName(String name){
        return roleRepository.findByName(name);
    }

    public Role createRole(Role role){
        if(role == null){
            return null;
        }
        return roleRepository.save(role);
    }
}
