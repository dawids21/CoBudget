package xyz.stasiak.cobudgetbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.stasiak.cobudgetbackend.security.jwt.TokenProvider;
import xyz.stasiak.cobudgetbackend.security.jwt.TokenProviderImpl;
import xyz.stasiak.cobudgetbackend.users.ApplicationUserRepository;

import java.time.Clock;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsServiceImpl userDetailsService(ApplicationUserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenProvider tokenProvider(SecurityProperties securityProperties) {
        return new TokenProviderImpl(Clock.systemUTC(), securityProperties);
    }

    @Bean
    public AuthUserService authUserService(ApplicationUserRepository userRepository, TokenProvider tokenProvider,
                                           SecurityProperties securityProperties) {
        return new AuthUserServiceImpl(userRepository, tokenProvider, new TokenCookieUtil(securityProperties.getJwt()));
    }
}
