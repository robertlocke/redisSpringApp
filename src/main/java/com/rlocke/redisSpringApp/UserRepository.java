package com.rlocke.redisSpringApp;


import com.rlocke.redisSpringApp.model.User;

import java.util.Map;

public interface UserRepository {

    void save(User user);
    Map<String, User> findAll();
    User findById(Integer id);
    void update(User user);
    void delete(Integer id);

}
