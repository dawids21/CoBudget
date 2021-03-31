package xyz.stasiak.cobudgetbackend.security;

public interface AuthUserService {

    void login(String email, String password, String accessToken, String refreshToken);

    void refresh(String accessToken, String refreshToken);
}
