package com.enigma.controller;

import com.enigma.entities.User;
import com.enigma.enumeration.Device;
import com.enigma.security.JwtResponse;
import com.enigma.services.AuthenticationService;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @PermitAll
    @PostMapping("/login")
    public JwtResponse createAuthenticationToken(@RequestBody User user, @RequestParam String type) throws Exception{
        return this.authenticationService.createAuthenticationToken(user, Device.getDeviceByLabel(type));
    }

    @PermitAll
    @PostMapping("/registration")
    public User registrationUser(@RequestBody User user){
        return this.userService.saveUser(user);
    }
}
