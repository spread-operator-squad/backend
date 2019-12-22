package com.enigma.services;

import com.enigma.entities.User;
import com.enigma.enumeration.Device;
import com.enigma.security.JwtResponse;

public interface AuthenticationService {
    JwtResponse createAuthenticationToken(User user, Device type) throws Exception;
}
