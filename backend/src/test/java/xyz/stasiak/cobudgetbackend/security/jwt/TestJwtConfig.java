package xyz.stasiak.cobudgetbackend.security.jwt;

import xyz.stasiak.cobudgetbackend.security.SecurityProperties;

class TestJwtConfig {

    final SecurityProperties securityProperties;

    public TestJwtConfig() {
        this.securityProperties = testSecurityProperties();
    }

    private SecurityProperties testSecurityProperties() {
        var securityProperties = new SecurityProperties();
        var jwtProperties = new SecurityProperties.Jwt();
        jwtProperties.setSecret("secret");
        jwtProperties.setAccessTokenCookieName("accessCookie");
        jwtProperties.setRefreshTokenCookieName("refreshCookie");
        jwtProperties.setAccessTokenExpirationDate(3600000L);
        jwtProperties.setRefreshTokenExpirationDate(7776000000L);
        jwtProperties.setSignUpUrl("/user/sign-up");
        securityProperties.setJwt(jwtProperties);
        return securityProperties;
    }
}
