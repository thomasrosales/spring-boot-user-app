package com.example.usersapp.controllers;

import com.example.usersapp.models.Post;
import com.example.usersapp.services.PlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/external-api")
public class ExternalApiController {

    @Autowired
    private PlaceholderService placeholderService;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(){
        List<Post> posts = placeholderService.getPosts();
        return ResponseEntity.ok(posts);
    }
}
