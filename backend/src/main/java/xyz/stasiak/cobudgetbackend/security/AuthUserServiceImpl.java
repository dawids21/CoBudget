package xyz.stasiak.cobudgetbackend.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import xyz.stasiak.cobudgetbackend.security.jwt.Token;
import xyz.stasiak.cobudgetbackend.security.jwt.TokenProvider;
import xyz.stasiak.cobudgetbackend.users.ApplicationUser;
import xyz.stasiak.cobudgetbackend.users.ApplicationUserRepository;

public class AuthUserServiceImpl implements AuthUserService {

    private final ApplicationUserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final TokenCookieUtil tokenCookieUtil;

    public AuthUserServiceImpl(ApplicationUserRepository userRepository, TokenProvider tokenProvider,
                               TokenCookieUtil tokenCookieUtil) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.tokenCookieUtil = tokenCookieUtil;
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken, String refreshToken) {
        String email = loginRequest.getEmail();
        ApplicationUser user = userRepository.findByEmail(email)
                                             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                                            "User with email " + email +
                                                                                            " not found"));

        boolean accessTokenValid = tokenProvider.validateToken(accessToken);
        boolean refreshTokenValid = tokenProvider.validateToken(refreshToken);

        HttpHeaders responseHeaders = new HttpHeaders();
        Token newAccessToken;
        Token newRefreshToken;
        if (!accessTokenValid && !refreshTokenValid) {
            newAccessToken = tokenProvider.generateAccessToken(user.getEmail());
            newRefreshToken = tokenProvider.generateRefreshToken(user.getEmail());
            addAccessTokenCookie(responseHeaders, newAccessToken);
            addRefreshTokenCookie(responseHeaders, newRefreshToken);
        }

        if (!accessTokenValid && refreshTokenValid) {
            newAccessToken = tokenProvider.generateAccessToken(user.getEmail());
            addAccessTokenCookie(responseHeaders, newAccessToken);
        }

        if (accessTokenValid && refreshTokenValid) {
            newAccessToken = tokenProvider.generateAccessToken(user.getEmail());
            newRefreshToken = tokenProvider.generateRefreshToken(user.getEmail());
            addAccessTokenCookie(responseHeaders, newAccessToken);
            addRefreshTokenCookie(responseHeaders, newRefreshToken);
        }

        LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.SUCCESS,
                                                        "Auth successful. Tokens are created in cookie.");
        return ResponseEntity.ok()
                             .headers(responseHeaders)
                             .body(loginResponse);

    }

    @Override
    public ResponseEntity<LoginResponse> refresh(String refreshToken) {
        boolean refreshTokenValid = tokenProvider.validateToken(refreshToken);
        if (!refreshTokenValid) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh Token is invalid!");
        }

        String currentUserEmail = tokenProvider.getUsernameFromToken(refreshToken);

        Token newAccessToken = tokenProvider.generateAccessToken(currentUserEmail);
        HttpHeaders responseHeaders = new HttpHeaders();
        addAccessTokenCookie(responseHeaders, newAccessToken);

        LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.SUCCESS,
                                                        "Auth successful. Tokens are created in cookie.");
        return ResponseEntity.ok()
                             .headers(responseHeaders)
                             .body(loginResponse);
    }

    @Override
    public ResponseEntity<?> logout() {
        HttpHeaders responseHeaders = new HttpHeaders();
        deleteAccessTokenCookie(responseHeaders);
        deleteRefreshTokenCookie(responseHeaders);
        return ResponseEntity.ok()
                             .headers(responseHeaders)
                             .build();
    }

    private void addAccessTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE,
                        tokenCookieUtil.createAccessTokenCookie(token.getTokenValue(), token.getDuration())
                                       .toString());
    }

    private void addRefreshTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE,
                        tokenCookieUtil.createRefreshTokenCookie(token.getTokenValue(), token.getDuration())
                                       .toString());
    }

    private void deleteAccessTokenCookie(HttpHeaders httpHeaders) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, tokenCookieUtil.deleteAccessTokenCookie()
                                                               .toString());
    }

    private void deleteRefreshTokenCookie(HttpHeaders httpHeaders) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, tokenCookieUtil.deleteRefreshTokenCookie()
                                                               .toString());
    }
}