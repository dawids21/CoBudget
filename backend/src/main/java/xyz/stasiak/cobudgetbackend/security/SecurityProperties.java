package xyz.stasiak.cobudgetbackend.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("security")
public class SecurityProperties {

    private Jwt jwt;

    public Jwt getJwt() {
        return jwt;
    }

    public void setJwt(Jwt jwt) {
        this.jwt = jwt;
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
}
