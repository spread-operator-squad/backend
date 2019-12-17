package com.enigma.controller;

import com.enigma.entities.User;
import com.enigma.services.CustomResponse;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<CustomResponse> getAllUser(){
        CustomResponse response = this.userService.findAll();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getUserById(@PathVariable String id){
        CustomResponse response = this.userService.findUserById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PostMapping
    public ResponseEntity<CustomResponse> saveUser(@RequestBody User user){
        CustomResponse response = this.userService.saveUser(user);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<CustomResponse> updateUser(@RequestBody User user){
        CustomResponse response = this.userService.updateUser(user);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteUserByID(@PathVariable String id){
        CustomResponse response = this.userService.deleteUserById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
