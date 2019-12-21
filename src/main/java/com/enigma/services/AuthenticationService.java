package com.enigma.services;

import com.enigma.entities.User;
import com.enigma.services.impl.CustomResponse;

public interface AuthenticationService {
    CustomResponse createAuthenticationToken(User user) throws Exception;
}
