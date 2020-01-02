package com.enigma.services;

import com.enigma.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User saveUser(User user);
    User findUserById(String id);
    User updateUser(User user);
    void deleteUserById(String id);
    User blockUserById(String id);
    User findUserByUsername(String username);
}
