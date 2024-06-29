package com.example.usersapp.repositories;


import com.example.usersapp.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

 // https://dzone.com/articles/add-custom-functionality-to-a-spring-data-reposito

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User getUserByUsername(String username) {
        Query query = entityManager.createNativeQuery("SELECT * FROM users where username = ?", User.class);
        query.setParameter(1, username);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> getAllUserLikeUsername(String username) {
        Query query = entityManager.createNativeQuery("SELECT * FROM users WHERE username LIKE ?", User.class);
        query.setParameter(1, username + "%");
        return query.getResultList();
    }
}
