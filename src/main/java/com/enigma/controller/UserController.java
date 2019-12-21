package com.enigma.controller;

import com.enigma.constans.RoleConstants;
import com.enigma.entities.User;
import com.enigma.services.impl.CustomResponse;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RolesAllowed(RoleConstants.ROLE_ADMINISTRATOR)
    @GetMapping
    public ResponseEntity<CustomResponse> getAllUser(){
        CustomResponse response = this.userService.findAll();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @RolesAllowed(RoleConstants.ROLE_ADMINISTRATOR)
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getUserById(@PathVariable String id){
        CustomResponse response = this.userService.findUserById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @RolesAllowed(RoleConstants.ROLE_ADMINISTRATOR)
    @PostMapping
    public ResponseEntity<CustomResponse> saveUser(@RequestBody User user){
        CustomResponse response = this.userService.saveUser(user);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @RolesAllowed(RoleConstants.ROLE_ADMINISTRATOR)
    @PutMapping
    public ResponseEntity<CustomResponse> updateUser(@RequestBody User user){
        CustomResponse response = this.userService.updateUser(user);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @RolesAllowed(RoleConstants.ROLE_ADMINISTRATOR)
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteUserByID(@PathVariable String id){
        CustomResponse response = this.userService.deleteUserById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @RolesAllowed(RoleConstants.ROLE_ADMINISTRATOR)
    @DeleteMapping("/{id}/block")
    public ResponseEntity<CustomResponse> blockUserById(@PathVariable String id){
        CustomResponse response = this.userService.blockUserById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
