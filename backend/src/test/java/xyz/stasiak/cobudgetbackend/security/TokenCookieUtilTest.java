package xyz.stasiak.cobudgetbackend.security;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TokenCookieUtilTest {

    private final SecurityProperties securityProperties = new TestSecurityConfig().securityProperties;
    private final TokenCookieUtil tokenCookieUtil = new TokenCookieUtil(securityProperties.getJwt());
}