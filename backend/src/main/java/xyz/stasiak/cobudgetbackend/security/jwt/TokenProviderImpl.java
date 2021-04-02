package xyz.stasiak.cobudgetbackend.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import xyz.stasiak.cobudgetbackend.security.SecurityProperties;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

public class TokenProviderImpl implements TokenProvider {

    private final Clock clock;
    private final SecurityProperties.Jwt jwtProperties;

    public TokenProviderImpl(Clock clock, SecurityProperties securityProperties) {
        this.clock = clock;
        this.jwtProperties = securityProperties.getJwt();
    }

    @Override
    public Token generateAccessToken(String subject) {
        var now = Instant.now(clock);
        var expiryDate = now.plusMillis(jwtProperties.getAccessTokenExpirationDate());
        String token = JWT.create()
                          .withIssuedAt(Date.from(now))
                          .withSubject(subject)
                          .withExpiresAt(Date.from(expiryDate))
                          .sign(Algorithm.HMAC512(jwtProperties.getSecret()
                                                               .getBytes()));
        return new Token(Token.TokenType.ACCESS, token, jwtProperties.getAccessTokenExpirationDate() / 1000,
                         expiryDate);
    }

    @Override
    public Token generateRefreshToken(String subject) {
        var now = Instant.now(clock);
        var expiryDate = now.plusMillis(jwtProperties.getRefreshTokenExpirationDate());
        String token = JWT.create()
                          .withIssuedAt(Date.from(now))
                          .withSubject(subject)
                          .withExpiresAt(Date.from(expiryDate))
                          .sign(Algorithm.HMAC512(jwtProperties.getSecret()
                                                               .getBytes()));
        return new Token(Token.TokenType.REFRESH, token, jwtProperties.getRefreshTokenExpirationDate() / 1000,
                         expiryDate);
    }

    @Override
    public String getUsernameFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(jwtProperties.getSecret()
                                                          .getBytes()))
                  .build()
                  .verify(token)
                  .getSubject();
    }

    @Override
    public Instant getExpiryDateFromToken(String token) {
        return JWT.require(Algorithm.HMAC512(jwtProperties.getSecret()
                                                          .getBytes()))
                  .build()
                  .verify(token)
                  .getExpiresAt()
                  .toInstant();
    }

    @Override
    public boolean validateToken(String token) {
        if (token == null) {
            return false;
        }
        try {
            JWT.require(Algorithm.HMAC512(jwtProperties.getSecret()
                                                       .getBytes()))
               .build()
               .verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }
}
