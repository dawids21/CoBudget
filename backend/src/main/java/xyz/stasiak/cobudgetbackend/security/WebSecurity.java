package xyz.stasiak.cobudgetbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import xyz.stasiak.cobudgetbackend.security.jwt.TokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SecurityProperties securityProperties;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    public WebSecurity(UserDetailsServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder,
                       SecurityProperties securityProperties, TokenAuthenticationFilter tokenAuthenticationFilter) {
        this.userDetailsService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.securityProperties = securityProperties;
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/auth/**")
            .permitAll()
            .antMatchers(HttpMethod.POST, securityProperties.getJwt()
                                                            .getSignUpUrl())
            .permitAll()
            .antMatchers(HttpMethod.GET, "/actuator/health")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(new RestAuthenticationEntryPoint())
            .and()
            .formLogin()
            .disable()
            .httpBasic()
            .disable();

        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowCredentials(true);

        var corsProperties = securityProperties.getCors();

        if (corsProperties.getAllowedOrigins() != null) {
            corsConfiguration.setAllowedOrigins(corsProperties.getAllowedOrigins());
        }

        if (corsProperties.getAllowedHeaders() != null) {
            corsConfiguration.setAllowedHeaders(corsProperties.getAllowedHeaders());
        }

        if (corsProperties.getAllowedMethods() != null) {
            corsConfiguration.setAllowedMethods(corsProperties.getAllowedMethods());
        }

        if (corsProperties.getMaxAge() != null) {
            corsConfiguration.setMaxAge(corsProperties.getMaxAge());
        }

        corsConfiguration.applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
