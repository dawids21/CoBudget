package xyz.stasiak.cobudgetbackend.security;


import org.springframework.http.ResponseCookie;

public class TokenCookieUtil {

    private final SecurityProperties.Jwt jwtProperties;
    private final boolean isDev;

    public TokenCookieUtil(SecurityProperties.Jwt jwtProperties, boolean isDev) {
        this.jwtProperties = jwtProperties;
        this.isDev = isDev;
    }

    // TODO secure(true) - check if it works everywhere
    public ResponseCookie createAccessTokenCookie(String token, Long duration) {
        return ResponseCookie.from(jwtProperties.getAccessTokenCookieName(), token)
                             .maxAge(duration)
                             .httpOnly(true)
                             .secure(!isDev)
                             .path("/")
                             .sameSite(isDev ? null : "None")
                             .build();
    }

    public ResponseCookie createRefreshTokenCookie(String token, Long duration) {
        return ResponseCookie.from(jwtProperties.getRefreshTokenCookieName(), token)
                             .maxAge(duration)
                             .httpOnly(true)
                             .secure(!isDev)
                             .path("/")
                             .sameSite(isDev ? null : "None")
                             .build();
    }

    public ResponseCookie deleteAccessTokenCookie() {
        return ResponseCookie.from(jwtProperties.getAccessTokenCookieName(), "")
                             .maxAge(0)
                             .httpOnly(true)
                             .secure(!isDev)
                             .path("/")
                             .sameSite(isDev ? null : "None")
                             .build();
    }

    public ResponseCookie deleteRefreshTokenCookie() {
        return ResponseCookie.from(jwtProperties.getRefreshTokenCookieName(), "")
                             .maxAge(0)
                             .httpOnly(true)
                             .secure(!isDev)
                             .path("/")
                             .sameSite("None")
                             .build();
    }
}
