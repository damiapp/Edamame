package com.edamame.service;

import com.edamame.model.User;

public interface UserService {
    void saveUser(User user);
    User findUserById(long id);
    User findUserByEmail(String email);
}
