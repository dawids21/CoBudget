package xyz.stasiak.cobudgetbackend.security.jwt;

import java.time.Instant;

public class TokenProviderImpl implements TokenProvider {

    @Override
    public Token generateAccessToken(String subject) {
        return null;
    }

    @Override
    public Token generateRefreshToken(String subject) {
        return null;
    }

    @Override
    public String getUsernameFromToken(String token) {
        return null;
    }

    @Override
    public Instant getExpiryDateFromToken(String token) {
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
}
