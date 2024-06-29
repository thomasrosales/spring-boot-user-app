package com.example.usersapp.repositories;

import com.example.usersapp.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile, String> {
    @Query("SELECT u.profile FROM User AS u WHERE u.id = ?1")
    Optional<Profile> findByUserId(String userID);
}
