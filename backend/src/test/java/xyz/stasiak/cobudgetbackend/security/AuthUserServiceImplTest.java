package xyz.stasiak.cobudgetbackend.security;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthUserServiceImplTest {

    private final AuthUserServiceImpl authUserService = new AuthUserServiceImpl();

    @Nested
    class Login {

        @Test
        void create_access_and_refresh_tokens_for_unauthenticated_user() {
            //TODO implement create_access_and_refresh_tokens_for_unauthenticated_user
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void create_access_token_when_refresh_token_is_valid() {
            //TODO implement createAccessTokenWhenRefreshTokenIsValid
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void create_new_access_and_refresh_tokens_for_authenticated_user() {
            //TODO implement createNewAccessAndRefreshTokensForAuthenticatedUser
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void return_success_for_successful_authentication() {
            //TODO implement returnSuccessForSuccessfulAuthentication
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Test
        void return_failure_for_unsuccessful_authentication() {
            //TODO implement returnFailureForUnsuccessfulAuthentication
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }

    private String testAccessToken() {
        return "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhYmMiLCJpYXQiOjE2MDk0ODQ0MDAsImV4cCI6MTYwOTU3MDgwMH0.d-nDkd8TjNQbXDGpu9YmvXxrcMRlg_AdljPEFPEr8qNKBjxQduMOPN0brqV47MhS5oTWS-ke8p7MYt-VzHz-Vg";
    }

    private String testRefreshToken() {
        return "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhYmMiLCJpYXQiOjE2MDk0ODQ0MDAsImV4cCI6MTYxNzI1NjgwMH0.8ASQW8-RYc1rp9KMhOKR3vk1q4KLFygit2QV3b2y0JifZnfhSv8SvZsVPexoiUJuWdSXl0prEJfL6eiYArlaDw";
    }
}