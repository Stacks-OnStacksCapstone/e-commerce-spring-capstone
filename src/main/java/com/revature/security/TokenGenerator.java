package com.revature.security;

import com.revature.dtos.Principal;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {
    private final JWTConfig jwtConfig;

    public TokenGenerator(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createToken(Principal principalUser) {
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(Integer.toString(principalUser.getId()))
                .setSubject(principalUser.getEmail())
                .setIssuer("Congo")
                .claim("isAdmin", principalUser.isAdmin())
                .claim("isActive", principalUser.isActive())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .signWith(jwtConfig.getSigAlg(),jwtConfig.getSigningKey());
        return tokenBuilder.compact();
    }
}
