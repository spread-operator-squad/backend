package com.enigma.services.impl;

import com.enigma.entities.User;
import com.enigma.security.JwtProperty;
import com.enigma.security.JwtResponse;
import com.enigma.security.UserPrincipalDetailsService;
import com.enigma.services.AuthenticationService;
import com.enigma.services.impl.CustomResponse;
import com.enigma.services.impl.Status;
import com.enigma.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    UserPrincipalDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtTokenUtil;

    @Override
    public CustomResponse createAuthenticationToken(User user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
            );
        } catch (BadCredentialsException e) {
            return new CustomResponse(new Status(HttpStatus.BAD_REQUEST, "Login failed"));
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUsername());

        return new CustomResponse(new Status(HttpStatus.OK, "Login Success"), new JwtResponse(JwtProperty.TYPE, jwtTokenUtil.generateToken(userDetails)));
    }
}
