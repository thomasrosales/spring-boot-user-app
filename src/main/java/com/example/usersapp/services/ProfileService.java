package com.example.usersapp.services;

import com.example.usersapp.entities.Profile;
import com.example.usersapp.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Optional<Profile> getProfileByUserId(String userId) {
        return profileRepository.findByUserId(userId);
    }
}
