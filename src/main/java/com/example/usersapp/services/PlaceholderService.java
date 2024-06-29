package com.example.usersapp.services;

import com.example.usersapp.models.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// https://examples.javacodegeeks.com/consuming-page-entity-response-from-resttemplate/
// https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/boot-features-webclient.html

@Service
public class PlaceholderService {
    private static final Logger log = LoggerFactory.getLogger(PlaceholderService.class);

    @Autowired
    private RestTemplate restTemplate;

    private static final String jsonPlaceholderURI = "https://jsonplaceholder.typicode.com/posts";


    public List<Post> getPosts() {
        List<Post> posts = List.of();

        try {
            ResponseEntity<List<Post>> response = restTemplate.exchange(
                    jsonPlaceholderURI,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Post>>() {
                    });
            if (response.hasBody()) {
                posts = response.getBody();
            }
        } catch (RestClientException e) {
            log.error(e.getMessage());
        }
        return posts;
    }

}
