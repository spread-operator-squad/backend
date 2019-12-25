package com.enigma.controller;

import com.enigma.entities.User;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return this.userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return this.userService.findUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@Valid @RequestBody User user){
        return this.userService.saveUser(user);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user){
        return this.userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByID(@PathVariable String id){
        this.userService.deleteUserById(id);
    }

    @DeleteMapping("/{id}/block")
    public User blockUserById(@PathVariable String id){
        return this.userService.blockUserById(id);
    }
}
