package com.enigma.services;

import com.enigma.entities.User;

public interface AuthenticationService {
    CustomResponse createAuthenticationToken(User user) throws Exception;
}
