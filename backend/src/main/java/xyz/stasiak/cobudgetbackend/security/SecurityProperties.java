package xyz.stasiak.cobudgetbackend.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("security")
public class SecurityProperties {

    private Jwt jwt;
    private Cors cors;

    public Jwt getJwt() {
        return jwt;
    }

    public void setJwt(Jwt jwt) {
        this.jwt = jwt;
    }

    public Cors getCors() {
        return cors;
    }

    public void setCors(Cors cors) {
        this.cors = cors;
    }

    public static class Jwt {
        //TODO change env vars in production

        private String secret;
        private long accessTokenExpirationDate;
        private long refreshTokenExpirationDate;
        private String tokenPrefix;
        private String headerString;
        private String signUpUrl;
        private String accessTokenCookieName;
        private String refreshTokenCookieName;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public long getAccessTokenExpirationDate() {
            return accessTokenExpirationDate;
        }

        public void setAccessTokenExpirationDate(long accessTokenExpirationDate) {
            this.accessTokenExpirationDate = accessTokenExpirationDate;
        }

        public long getRefreshTokenExpirationDate() {
            return refreshTokenExpirationDate;
        }

        public void setRefreshTokenExpirationDate(long refreshTokenExpirationDate) {
            this.refreshTokenExpirationDate = refreshTokenExpirationDate;
        }

        public String getTokenPrefix() {
            return tokenPrefix;
        }

        public void setTokenPrefix(String tokenPrefix) {
            this.tokenPrefix = tokenPrefix;
        }

        public String getHeaderString() {
            return headerString;
        }

        public void setHeaderString(String headerString) {
            this.headerString = headerString;
        }

        public String getSignUpUrl() {
            return signUpUrl;
        }

        public void setSignUpUrl(String signUpUrl) {
            this.signUpUrl = signUpUrl;
        }

        public String getAccessTokenCookieName() {
            return accessTokenCookieName;
        }

        public void setAccessTokenCookieName(String accessTokenCookieName) {
            this.accessTokenCookieName = accessTokenCookieName;
        }

        public String getRefreshTokenCookieName() {
            return refreshTokenCookieName;
        }

        public void setRefreshTokenCookieName(String refreshTokenCookieName) {
            this.refreshTokenCookieName = refreshTokenCookieName;
        }
    }

    public static class Cors {

        private List<String> allowedOrigins;
        private List<String> allowedHeaders;
        private List<String> allowedMethods;
        private Long maxAge;

        public List<String> getAllowedOrigins() {
            return allowedOrigins;
        }

        public void setAllowedOrigins(List<String> allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
        }

        public List<String> getAllowedHeaders() {
            return allowedHeaders;
        }

        public void setAllowedHeaders(List<String> allowedHeaders) {
            this.allowedHeaders = allowedHeaders;
        }

        public List<String> getAllowedMethods() {
            return allowedMethods;
        }

        public void setAllowedMethods(List<String> allowedMethods) {
            this.allowedMethods = allowedMethods;
        }

        public Long getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(Long maxAge) {
            this.maxAge = maxAge;
        }
    }
}
