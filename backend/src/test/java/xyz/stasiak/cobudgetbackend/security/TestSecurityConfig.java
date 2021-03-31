package xyz.stasiak.cobudgetbackend.security;

class TestSecurityConfig {

    final SecurityProperties securityProperties;

    public TestSecurityConfig() {
        this.securityProperties = testSecurityProperties();
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
