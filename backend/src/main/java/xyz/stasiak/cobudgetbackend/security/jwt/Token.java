package xyz.stasiak.cobudgetbackend.security.jwt;


import java.time.LocalDateTime;
import java.util.Objects;

public class Token {

    private TokenType tokenType;
    private String tokenValue;
    private Long duration;
    private LocalDateTime expiryDate;

    public Token(TokenType tokenType, String tokenValue, Long duration, LocalDateTime expiryDate) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
        this.duration = duration;
        this.expiryDate = expiryDate;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Token token = (Token) o;
        return getTokenType() == token.getTokenType() && Objects.equals(getTokenValue(), token.getTokenValue()) &&
               Objects.equals(getDuration(), token.getDuration()) &&
               Objects.equals(getExpiryDate(), token.getExpiryDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTokenType(), getTokenValue(), getDuration(), getExpiryDate());
    }

    @Override
    public String toString() {
        return "Token{" + "tokenType=" + tokenType + ", tokenValue='" + tokenValue + '\'' + ", duration=" + duration +
               ", expiryDate=" + expiryDate + '}';
    }

    public enum TokenType {
        ACCESS, REFRESH
    }
}
