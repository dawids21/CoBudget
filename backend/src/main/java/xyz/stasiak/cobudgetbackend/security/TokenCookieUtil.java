package xyz.stasiak.cobudgetbackend.security;


import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;

public class CookieUtil {

    private final SecurityProperties.Jwt jwtProperties;

    public CookieUtil(SecurityProperties.Jwt jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    // TODO secure(true)?
    public HttpCookie createAccessTokenCookie(String token, Long duration) {
        return ResponseCookie.from(jwtProperties.getAccessTokenCookieName(), token)
                             .maxAge(duration)
                             .httpOnly(true)
                             .path("/")
                             .build();
    }

    public HttpCookie createRefreshTokenCookie(String token, Long duration) {
        return ResponseCookie.from(jwtProperties.getRefreshTokenCookieName(), token)
                             .maxAge(duration)
                             .httpOnly(true)
                             .path("/")
                             .build();
    }

    public HttpCookie deleteAccessTokenCookie() {
        return ResponseCookie.from(jwtProperties.getAccessTokenCookieName(), "")
                             .maxAge(0)
                             .httpOnly(true)
                             .path("/")
                             .build();
    }
}
