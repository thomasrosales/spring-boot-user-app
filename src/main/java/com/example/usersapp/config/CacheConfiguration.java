package com.example.usersapp.config;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

// https://www.baeldung.com/spring-cache-tutorial

@EnableCaching
@Configuration
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager(@Autowired RedissonClient redissonClient) {
        Map<String, CacheConfig> caches = new HashMap<>();
        caches.put("users", new CacheConfig());
        caches.put("roles", new CacheConfig());
        caches.put("address", new CacheConfig());
        return new RedissonSpringCacheManager(redissonClient, caches);
        // return new ConcurrentMapCacheManager("users", "role", "address");
    }
}
