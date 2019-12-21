package com.enigma.controller;

import com.enigma.constans.RoleConstants;
import com.enigma.entities.User;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public User saveUser(@RequestBody User user){
        return this.userService.saveUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return this.userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserByID(@PathVariable String id){
        this.userService.deleteUserById(id);
    }

    @DeleteMapping("/{id}/block")
    public User blockUserById(@PathVariable String id){
        return this.userService.blockUserById(id);
    }
}
