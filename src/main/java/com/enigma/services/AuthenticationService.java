package com.enigma.services;

import com.enigma.entities.User;
import com.enigma.enumeration.Device;
import com.enigma.services.impl.CustomResponse;

public interface AuthenticationService {
    CustomResponse createAuthenticationToken(User user, Device type) throws Exception;
}
