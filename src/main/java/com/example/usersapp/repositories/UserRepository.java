package com.example.usersapp.repositories;

import com.example.usersapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String>, UserCustomRepository {
    public Optional<User> findByUsernameAndPassword(String username, String password);  // implementado por spring
}
