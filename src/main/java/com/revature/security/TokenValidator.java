package com.revature.security;

import com.revature.dtos.Principal;
import com.revature.exceptions.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenValidator {

    private final JWTConfig jwtConfig;

    public TokenValidator(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public Optional<Principal> parseToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
            return Optional.of(new Principal(Integer.parseInt(claims.getId()), claims.getSubject(), claims.get("isAdmin",Boolean.class), claims.get("isActive",Boolean.class)));

        } catch (Exception e) {
            e.printStackTrace();
            throw new UnauthorizedException(e.getMessage());
        }
    }

    public int getDefaultTokenExpiry() {
        return jwtConfig.getExpiration();
    }
}
