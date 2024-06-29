package com.example.usersapp.services;

//import com.example.usersapp.models.User;

import com.example.usersapp.entities.User;
import com.example.usersapp.repositories.ProfileRepository;
import com.example.usersapp.repositories.UserRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private Faker faker;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    private List<User> users = new ArrayList<>();


    @PostConstruct  // despues de construido el bean se ejecuta este metodo
    public void init() {
        /*
        for (int i = 0; i < 10; i++) {
            users.add(new User(
                    faker.name().username(),
                    faker.dragonBall().character(),
                    faker.funnyName().name())
            );
        }
         */
    }

    private Boolean userExists(User user) {
        return users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));
    }


    public Page<User> getUsers(int page, int size) {
        /*if (username == null || username.isEmpty()) {
            return (List<User>) userRepository.findAll();
        }

         */

        // return userRepository.getAllUserLikeUsername(username);
        return userRepository.findAll(PageRequest.of(page, size));
        // return users.stream().filter(u -> u.getUsername().startsWith(username)).collect(Collectors.toList());

    }

    @Cacheable(value = "users")
    public User getUserByUsername(String username) {
        log.info("Getting user by username {}", username);
        return userRepository.getUserByUsername(username);
        // return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    public User createUser(User user) {
        if (userRepository.getUserByUsername(user.getUsername()) != null) {
            return null;
        }
        // users.add(user);
        if (user.getProfile() != null) {
            profileRepository.save(user.getProfile());
        }
        return userRepository.save(user);
    }

    public User updateUser(User user, String username) {
        User userToBeUpdated = getUserByUsername(username);
        if (userToBeUpdated == null) {
            return null;
        }
        userToBeUpdated.setUsername(user.getUsername());
        userToBeUpdated.setPassword(user.getPassword());
        // userToBeUpdated.setNickname(user.getNickname());
        userRepository.save(userToBeUpdated);
        return userToBeUpdated;
    }

    public User removeUser(String username) {
        User userToBeDeleted = getUserByUsername(username);
        if (userToBeDeleted == null) {
            return null;
        }
        userRepository.delete(userToBeDeleted);
        // users.remove(userToBeDeleted);
        return userToBeDeleted;
    }
}
