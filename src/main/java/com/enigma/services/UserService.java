package com.enigma.services;

import com.enigma.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User saveUser(User user);
    User findUserById(String id);
    User updateUser(User user);
    void deleteUserById(String id);
    User blockUserById(String id);
}
