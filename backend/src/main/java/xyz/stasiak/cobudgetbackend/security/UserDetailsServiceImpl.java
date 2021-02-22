package xyz.stasiak.cobudgetbackend.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import xyz.stasiak.cobudgetbackend.users.ApplicationUser;
import xyz.stasiak.cobudgetbackend.users.ApplicationUserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository userRepository;

    public UserDetailsServiceImpl(ApplicationUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findByEmail(s)
                                             .orElseThrow(
                                                      () -> new UsernameNotFoundException("User " + s + " not found"));
        return User.builder()
                   .username(user.getEmail())
                   .password(user.getPassword())
                   .authorities("ROLE_USER")
                   .build();
    }

}
