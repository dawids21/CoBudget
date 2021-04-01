package xyz.stasiak.cobudgetbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

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
        }

        @Test
        void create_access_token_when_refresh_token_is_valid() {
            //TODO implement createAccessTokenWhenRefreshTokenIsValid
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void create_new_access_and_refresh_tokens_for_authenticated_user() {
            //TODO implement createNewAccessAndRefreshTokensForAuthenticatedUser
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void return_success_for_successful_authentication() {
            //TODO implement returnSuccessForSuccessfulAuthentication
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void return_failure_for_unsuccessful_authentication() {
            //TODO implement returnFailureForUnsuccessfulAuthentication
            throw new UnsupportedOperationException("Not implemented yet");
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