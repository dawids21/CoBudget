package xyz.stasiak.cobudgetbackend.security.jwt;

public interface TokenProvider {

    boolean validateToken(String token);

    String getUsernameFromToken(String token);
}
