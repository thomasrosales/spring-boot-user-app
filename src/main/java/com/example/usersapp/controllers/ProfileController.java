package com.example.usersapp.controllers;

import com.example.usersapp.entities.Profile;
import com.example.usersapp.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


// FIXME: ES UN CASO DE PRUEBA NO TIENE SENTIDO CON PROFILE DEL LADO DEL USUARIO

@RestController
@RequestMapping("/users/{userId}/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<Optional<Profile>> getProfile(@PathVariable String userId) {
        Optional<Profile> profile = profileService.getProfileByUserId(userId);
        if (profile.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile does not exist for user %s", userId));
        }
        return ResponseEntity.ok().body(profile);
    }
}
