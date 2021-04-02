package xyz.stasiak.cobudgetbackend.security;

import org.springframework.http.ResponseEntity;

public interface AuthUserService {

    ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken, String refreshToken);

    ResponseEntity<LoginResponse> refresh(String refreshToken);

    ResponseEntity<?> logout();
}
