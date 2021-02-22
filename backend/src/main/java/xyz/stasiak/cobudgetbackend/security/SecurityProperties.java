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

        private String secret;
        private long expirationDate;
        private String tokenPrefix;
        private String headerString;
        private String signUpUrl;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public long getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(long expirationDate) {
            this.expirationDate = expirationDate;
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
    }
}
