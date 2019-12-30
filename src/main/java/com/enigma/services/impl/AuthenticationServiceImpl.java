package com.enigma.services.impl;

import com.enigma.entities.User;
import com.enigma.enumeration.Device;
import com.enigma.exceptions.BadRequestException;
import com.enigma.security.JwtProperty;
import com.enigma.security.JwtResponse;
import com.enigma.services.AuthenticationService;
import com.enigma.services.UserService;
import com.enigma.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserPrincipalDetailsServiceImpl userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtTokenUtil;

    @Override
    public JwtResponse createAuthenticationToken(User user, Device type) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Username and password is invalid");
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUsername());

        // Find user ID from given username
        String userId = userService.findUserByUsername(user.getUsername()).getId();
        return new JwtResponse(JwtProperty.TYPE, jwtTokenUtil.generateToken(userDetails, type, userId));
    }
}
