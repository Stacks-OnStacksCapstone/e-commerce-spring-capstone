package com.revature.services;

import com.revature.dtos.Principal;
import com.revature.exceptions.InvalidTokenException;
import com.revature.exceptions.InvalidUserInputException;
import com.revature.exceptions.UnauthorizedException;
import com.revature.security.TokenGenerator;
import com.revature.security.TokenValidator;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class TokenService {
    private final TokenValidator tokenValidator;
    private final TokenGenerator tokenGenerator;

    public TokenService(TokenGenerator tokenGenerator, TokenValidator tokenValidator) {
        this.tokenGenerator = tokenGenerator;
        this.tokenValidator = tokenValidator;
    }

    public String generateToken(Principal subject) {
        if (!isPrincipalValid(subject)) throw new InvalidUserInputException("Provided with invalid principal object");
        return tokenGenerator.createToken(subject);
    }

    public boolean isTokenValid(String token) {
        if (token == null || token.trim().equals("")) return false;
        return tokenValidator.parseToken(token).isPresent();
    }

    public Principal extractTokenDetails(String token) {
        if (token == null || token.trim().equals("")) throw new UnauthorizedException("No authentication token found on request");
        return tokenValidator.parseToken(token).orElseThrow(InvalidTokenException::new);
    }

    public int getDefaultTokenExpiry() {
        return tokenValidator.getDefaultTokenExpiry();
    }

    private boolean isPrincipalValid(Principal subject) {
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");
        return (subject != null && notNullOrEmpty.test(Integer.toString(subject.getId())) && notNullOrEmpty.test(subject.getEmail()));
    }
}
