package com.enigma.services;

import com.enigma.entities.User;
import com.enigma.services.impl.CustomResponse;

public interface UserService {
    CustomResponse findAll();
    CustomResponse saveUser(User user);
    CustomResponse findUserById(String id);
    CustomResponse updateUser(User user);
    CustomResponse deleteUserById(String id);
    CustomResponse blockUserById(String id);
}
