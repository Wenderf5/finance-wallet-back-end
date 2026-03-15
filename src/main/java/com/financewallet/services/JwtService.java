package com.financewallet.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtService {

    private static final String SECRET = System.getenv("JWT_SECRET");

    public String generateToken(String userName, String email) {
        return JWT.create()
                .withSubject(userName)
                .withClaim("email", email)
                .sign(Algorithm.HMAC256(SECRET));
    }
}

