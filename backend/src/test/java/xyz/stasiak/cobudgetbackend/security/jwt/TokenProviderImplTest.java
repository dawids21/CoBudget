package xyz.stasiak.cobudgetbackend.security.jwt;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import xyz.stasiak.cobudgetbackend.security.SecurityProperties;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TokenProviderImplTest {

    private final Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    private final SecurityProperties securityProperties = new SecurityTestConfig().securityProperties;
    private final TokenProvider tokenProvider = new TokenProviderImpl(clock, securityProperties);

    @Test
    void set_expiry_date_for_access_token_based_on_properties() {
        var token = testAccessToken();
        assertThat(token.getExpiryDate()).isEqualTo(Instant.now(clock)
                                                           .plusMillis(securityProperties.getJwt()
                                                                                         .getAccessTokenExpirationDate()));
    }


    @Test
    void set_expiry_date_for_refresh_token_based_on_properties() {
        var token = testRefreshToken();
        assertThat(token.getExpiryDate()).isEqualTo(Instant.now(clock)
                                                           .plusMillis(securityProperties.getJwt()
                                                                                         .getRefreshTokenExpirationDate()));
    }

    @Test
    void access_token_should_have_correct_type() {
        var token = testAccessToken();
        assertThat(token.getTokenType()).isEqualTo(Token.TokenType.ACCESS);
    }

    @Test
    void refresh_token_should_have_correct_type() {
        var token = testRefreshToken();
        assertThat(token.getTokenType()).isEqualTo(Token.TokenType.REFRESH);
    }

    @Test
    void return_subject_of_the_token() {
        var token = testAccessToken();
        assertThat(tokenProvider.getUsernameFromToken(token.getTokenValue())).isEqualTo("abc");
    }

    @Test
    void return_expiry_date_of_the_token() {
        var token = testAccessToken();
        var expiryDate = token.getExpiryDate();
        assertThat(tokenProvider.getExpiryDateFromToken(token.getTokenValue())).isEqualTo(
                 expiryDate.truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    void validate_correct_token() {
        var tokenValue = testAccessToken().getTokenValue();
        assertThat(tokenProvider.validateToken(tokenValue)).isTrue();
    }

    @Test
    void return_false_for_token_that_is_invalid() {
        var tokenValue = testAccessToken().getTokenValue();
        var changedTokenValue = tokenValue.concat("a");
        assertThat(tokenProvider.validateToken(changedTokenValue)).isFalse();
    }

    private Token testAccessToken() {
        return tokenProvider.generateAccessToken("abc");
    }

    private Token testRefreshToken() {
        return tokenProvider.generateRefreshToken("abc");
    }

}