package xyz.stasiak.cobudgetbackend.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import xyz.stasiak.cobudgetbackend.security.SecurityProperties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final SecurityProperties securityProperties;

    public JWTAuthorizationFilter(AuthenticationManager authManager, SecurityProperties securityProperties) {
        super(authManager);
        this.securityProperties = securityProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
             throws IOException, ServletException {
        String header = req.getHeader(securityProperties.getJwt()
                                                        .getHeaderString());

        if (header == null || !header.startsWith(securityProperties.getJwt()
                                                                   .getTokenPrefix())) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(securityProperties.getJwt()
                                                           .getHeaderString());

        if (token != null) {
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(securityProperties.getJwt()
                                                                          .getSecret()
                                                                          .getBytes()))
                             .build()
                             .verify(token.replace(securityProperties.getJwt()
                                                                     .getTokenPrefix(), ""))
                             .getSubject();

            if (user != null) {
                // new arraylist means authorities
                return new UsernamePasswordAuthenticationToken(user, null,
                                                               List.of(new SimpleGrantedAuthority("ROLE_USER")));
            }

            return null;
        }

        return null;
    }
}