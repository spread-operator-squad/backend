package com.enigma.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class JwtResponse {
    String type;
    String token;
}
