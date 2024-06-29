package com.example.usersapp.repositories;

import com.example.usersapp.entities.User;

import java.util.List;

public interface UserCustomRepository {
    public User getUserByUsername(String username);
    public List<User> getAllUserLikeUsername(String username);
}
