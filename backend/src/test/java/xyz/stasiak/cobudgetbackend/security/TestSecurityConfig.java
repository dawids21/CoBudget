package xyz.stasiak.cobudgetbackend.security;

import org.mockito.Mockito;
import xyz.stasiak.cobudgetbackend.users.ApplicationUser;
import xyz.stasiak.cobudgetbackend.users.ApplicationUserRepository;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestSecurityConfig extends SecurityConfig {

    public TestSecurityConfig() {
    }

    AuthUserService testAuthUserService() {
        var securityProperties = testSecurityProperties();
        return authUserService(testApplicationUserRepository(), tokenProvider(securityProperties), securityProperties);
    }

    SecurityProperties testSecurityProperties() {
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

    private ApplicationUserRepository testApplicationUserRepository() {
        var userRepository = mock(ApplicationUserRepository.class);
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(
                 Optional.of(new ApplicationUser("abc@def.com", "pass", "John")));
        return userRepository;
    }
}
