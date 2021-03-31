package xyz.stasiak.cobudgetbackend.security;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TokenCookieUtilTest {

    private final SecurityProperties securityProperties = new TestSecurityConfig().securityProperties;
    private final TokenCookieUtil tokenCookieUtil = new TokenCookieUtil(securityProperties.getJwt());

    @Nested
    class AccessCookie {

        @Test
        void is_httponly() {
            var cookie = tokenCookieUtil.createAccessTokenCookie("123", 30000L);
            assertThat(cookie.isHttpOnly()).isTrue();
        }

        @Test
        void is_created_for_root_path() {
            var cookie = tokenCookieUtil.createAccessTokenCookie("123", 30000L);
            assertThat(cookie.getPath()).isEqualTo("/");
        }
    }
   
    @Nested
    class RefreshCookie {

        @Test
        void is_httponly() {
            var cookie = tokenCookieUtil.createRefreshTokenCookie("123", 30000L);
            assertThat(cookie.isHttpOnly()).isTrue();
        }

        @Test
        void is_created_for_root_path() {
            var cookie = tokenCookieUtil.createRefreshTokenCookie("123", 30000L);
            assertThat(cookie.getPath()).isEqualTo("/");
        }
    }
}