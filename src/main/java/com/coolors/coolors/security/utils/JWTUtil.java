package com.coolors.coolors.security.utils;

import org.springframework.security.core.userdetails.UserDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JWTUtil {
    public String gerarToken(UserDetails userDetails) {
        return JWT.create().withIssuer("Marquardt")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime()))
                .withSubject(userDetails.getUsername())
                .sign(Algorithm.HMAC256(userDetails.getPassword()));
    }

    public String getNome(String token) {
        return JWT.decode(token).getSubject();
    }
}
