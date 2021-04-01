package xyz.stasiak.cobudgetbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthUserServiceImplTest {

    private final SecurityProperties.Jwt jwtProperties = new TestSecurityConfig().testSecurityProperties()
                                                                                 .getJwt();
    private final AuthUserService authUserService = new TestSecurityConfig().testAuthUserService();

    @Nested
    class Login {

        @Test
        void create_access_and_refresh_tokens_for_unauthenticated_user() {
            var response = authUserService.login(testLoginRequest(), "", "");
            var cookies = response.getHeaders()
                                  .get(HttpHeaders.SET_COOKIE);
            assertThat(cookies).hasSize(2);
            assertThat(cookies).anyMatch(s -> s.contains("access"));
            assertThat(cookies).anyMatch(s -> s.contains("refresh"));
        }

        @Test
        void create_access_token_when_refresh_token_is_valid() {
            var response = authUserService.login(testLoginRequest(), "", testRefreshToken());
            var cookies = response.getHeaders()
                                  .get(HttpHeaders.SET_COOKIE);
            assertThat(cookies).hasSize(1);
            assertThat(cookies).anyMatch(s -> s.contains("access"));
            assertThat(cookies).anyMatch(s -> !s.contains("refresh"));
        }

        @Test
        void create_new_access_and_refresh_tokens_for_authenticated_user() {
            var response = authUserService.login(testLoginRequest(), testAccessToken(), testRefreshToken());
            var cookies = response.getHeaders()
                                  .get(HttpHeaders.SET_COOKIE);
            assertThat(cookies).hasSize(2);
            assertThat(cookies).anyMatch(s -> s.contains("access"));
            assertThat(cookies).anyMatch(s -> s.contains("refresh"));
        }

        @Test
        void return_success_for_successful_authentication() {
            var response = authUserService.login(testLoginRequest(), "", "");
            assertThat(response.getBody()
                               .getStatus()).isEqualTo(LoginResponse.SuccessFailure.SUCCESS);
        }

        @Test
        void return_failure_for_unsuccessful_authentication() {
            assertThatThrownBy(
                     () -> authUserService.login(new LoginRequest("wrong@mail.com", "asd"), "", "")).isInstanceOf(
                     ResponseStatusException.class)
                                                                                                    .hasMessageContaining(
                                                                                                             "not found")
                                                                                                    .hasFieldOrPropertyWithValue(
                                                                                                             "status",
                                                                                                             HttpStatus.NOT_FOUND);
        }
    }

    private LoginRequest testLoginRequest() {
        return new LoginRequest("abc@def.com", "pass");
    }

    private String testAccessToken() {
        var now = Instant.now(Clock.systemUTC());
        return JWT.create()
                  .withIssuedAt(Date.from(now))
                  .withSubject("abc@def.com")
                  .withExpiresAt(Date.from(now.plusMillis(jwtProperties.getAccessTokenExpirationDate())))
                  .sign(Algorithm.HMAC512(jwtProperties.getSecret()));
    }

    private String testRefreshToken() {
        var now = Instant.now(Clock.systemUTC());
        return JWT.create()
                  .withIssuedAt(Date.from(now))
                  .withSubject("abc@def.com")
                  .withExpiresAt(Date.from(now.plusMillis(jwtProperties.getRefreshTokenExpirationDate())))
                  .sign(Algorithm.HMAC512(jwtProperties.getSecret()));
    }

    private SecurityProperties testSecurityProperties() {
        var securityProperties = new SecurityProperties();
        var jwtProperties = new SecurityProperties.Jwt();
        jwtProperties.setSecret("secret");
        jwtProperties.setAccessTokenCookieName("accessCookie");
        jwtProperties.setRefreshTokenCookieName("refreshCookie");
        jwtProperties.setHeaderString("Authorization");
        jwtProperties.setTokenPrefix("Bearer ");
        jwtProperties.setAccessTokenExpirationDate(3600000L);
        jwtProperties.setRefreshTokenExpirationDate(7776000000L);
        jwtProperties.setSignUpUrl("/user/sign-up");
        securityProperties.setJwt(jwtProperties);
        return securityProperties;
    }

}