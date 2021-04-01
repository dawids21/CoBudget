package xyz.stasiak.cobudgetbackend.security;


import org.springframework.http.ResponseCookie;

public class TokenCookieUtil {

    private final SecurityProperties.Jwt jwtProperties;

    public TokenCookieUtil(SecurityProperties.Jwt jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    // TODO secure(true) - check if it works everywhere
    public ResponseCookie createAccessTokenCookie(String token, Long duration) {
        return ResponseCookie.from(jwtProperties.getAccessTokenCookieName(), token)
                             .maxAge(duration)
                             .httpOnly(true)
                             .path("/")
                             .secure(true)
                             .build();
    }

    public ResponseCookie createRefreshTokenCookie(String token, Long duration) {
        return ResponseCookie.from(jwtProperties.getRefreshTokenCookieName(), token)
                             .maxAge(duration)
                             .httpOnly(true)
                             .path("/")
                             .secure(true)
                             .build();
    }

    public ResponseCookie deleteAccessTokenCookie() {
        return ResponseCookie.from(jwtProperties.getAccessTokenCookieName(), "")
                             .maxAge(0)
                             .httpOnly(true)
                             .path("/")
                             .build();
    }
}
