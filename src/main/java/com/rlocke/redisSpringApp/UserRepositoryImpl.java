package com.rlocke.redisSpringApp;

import com.rlocke.redisSpringApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {


    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;


    public UserRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(User user) {

        hashOperations.put("USER", user.getId().toString(), user);
    }

    @Override
    public Map<String, User> findAll() {
        return hashOperations.entries("USER");
    }

    @Override
    public User findById(Integer id) {
        return (User)hashOperations.get("USER", id.toString());
    }

    @Override
    public void update(User user) {
        save(user);
    }

    @Override
    public void delete(Integer id) {

        hashOperations.delete("USER", id.toString());
    }
}
