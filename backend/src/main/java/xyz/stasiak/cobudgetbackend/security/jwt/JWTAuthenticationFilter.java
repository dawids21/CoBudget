package xyz.stasiak.cobudgetbackend.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xyz.stasiak.cobudgetbackend.security.SecurityProperties;
import xyz.stasiak.cobudgetbackend.users.ApplicationUser;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final SecurityProperties securityProperties;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, SecurityProperties securityProperties) {
        this.authenticationManager = authenticationManager;
        this.securityProperties = securityProperties;

        setFilterProcessesUrl("/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
             throws AuthenticationException {
        try {
            ApplicationUser credentials = new ObjectMapper().readValue(req.getInputStream(), ApplicationUser.class);

            return authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(),
                                                             List.of(new SimpleGrantedAuthority("ROLE_USER"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException {
        String token = JWT.create()
                          .withSubject(((User) auth.getPrincipal()).getUsername())
                          .withExpiresAt(Date.from(Instant.now()
                                                          .plusMillis(securityProperties.getJwt()
                                                                                        .getExpirationDate())))
                          .sign(Algorithm.HMAC512(securityProperties.getJwt()
                                                                    .getSecret()
                                                                    .getBytes()));

        res.setHeader("Content-Type", "application/json");
        String body = "{\"token\": \"" + token + "\"}";
        res.getWriter()
           .write(body);
        res.getWriter()
           .flush();
    }
}
