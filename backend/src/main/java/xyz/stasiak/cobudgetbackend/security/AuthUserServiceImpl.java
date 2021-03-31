package xyz.stasiak.cobudgetbackend.security;

import org.springframework.http.ResponseEntity;

public class AuthUserServiceImpl implements AuthUserService {

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken, String refreshToken) {
        return null;
    }

    @Override
    public ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken) {
        return null;
    }
}
