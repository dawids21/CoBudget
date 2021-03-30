package xyz.stasiak.cobudgetbackend.security.jwt;

import java.time.Instant;

public interface TokenProvider {

    Token generateAccessToken(String subject);

    Token generateRefreshToken(String subject);

    String getUsernameFromToken(String token);

    Instant getExpiryDateFromToken(String token);

    boolean validateToken(String token);
}
