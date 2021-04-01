package xyz.stasiak.cobudgetbackend.security;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthUserService userService;

    public AuthController(AuthenticationManager authenticationManager, AuthUserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@CookieValue(name = "accessToken", required = false) String accessToken,
                                               @CookieValue(name = "refreshToken", required = false) String refreshToken,
                                               @Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);

        return userService.login(loginRequest, accessToken, refreshToken);
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<LoginResponse> refreshToken(
             @CookieValue(name = "refreshToken", required = false) String refreshToken) {
        return userService.refresh(refreshToken);
    }
}
