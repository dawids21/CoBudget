package xyz.stasiak.cobudgetbackend.security.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TokenProviderImplTest {

    private TokenProvider tokenProvider;
    private Clock clock;

    @BeforeEach
    void setUp() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        tokenProvider = new TokenProviderImpl(clock, new SecurityTestConfig().securityProperties);
    }

    @Test
    void set_expiry_date_for_access_token_based_on_properties() {
        //TODO implement set_expiry_date_for_access_token_based_on_properties
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void set_expiry_date_for_refresh_token_based_on_properties() {
        //TODO implement set_expiry_date_for_access_token_based_on_properties
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void access_token_should_have_correct_type() {
        //TODO implement access_token_should_have_correct_type
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void refresh_token_should_have_correct_type() {
        //TODO implement access_token_should_have_correct_type
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void return_subject_of_the_token() {
        //TODO implement return_subject_of_the_token
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void return_expiry_date_of_the_token() {
        //TODO implement return_expiry_date_of_the_token
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void validate_correct_token() {
        //TODO implement validate_correct_token
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void return_false_for_token_that_is_invalid() {
        //TODO implement return_false_for_token_that_is_invalid
        throw new UnsupportedOperationException("Not implemented yet");
    }
}