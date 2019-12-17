package com.enigma.controller;

import com.enigma.entities.User;
import com.enigma.services.AuthenticationService;
import com.enigma.services.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @PermitAll
    @PostMapping("/login")
    public ResponseEntity<CustomResponse> createAuthenticationToken(@RequestBody User user) throws Exception{
        CustomResponse response = this.authenticationService.createAuthenticationToken(user);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
