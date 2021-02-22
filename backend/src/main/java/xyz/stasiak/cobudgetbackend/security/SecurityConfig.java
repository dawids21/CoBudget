package xyz.stasiak.cobudgetbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.stasiak.cobudgetbackend.users.ApplicationUserRepository;

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
}
