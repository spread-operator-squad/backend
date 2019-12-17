package com.enigma.services;

import com.enigma.constans.ResponseMessageConstants;
import com.enigma.entities.User;
import com.enigma.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public CustomResponse findAll(){
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageConstants.SUCCESS_GET_USERS), this.userRepository.findAll());
    }

    @Override
    public CustomResponse saveUser(User user){
        return new CustomResponse(new Status(HttpStatus.CREATED,ResponseMessageConstants.SUCCESS_SAVE_USER), this.userRepository.save(user));
    }

    @Override
    public CustomResponse findUserById(String id){
        if (!(this.userRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "User is not found"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageConstants.SUCCESS_GET_USER), this.userRepository.findById(id).get());
    }

    @Override
    public CustomResponse updateUser(User user){
        if (this.findUserById(user.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findUserById(user.getId());
        else return new CustomResponse(new Status(HttpStatus.OK,ResponseMessageConstants.SUCCESS_UPDATE_USER), this.saveUser(user).getData());
    }

    @Override
    public CustomResponse deleteUserById(String id) {
        this.userRepository.delete((User) this.findUserById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT,ResponseMessageConstants.SUCCESS_DELETE_USER));
    }
}
