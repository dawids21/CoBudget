package xyz.stasiak.cobudgetbackend.users;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import xyz.stasiak.cobudgetbackend.users.config.UserConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ApplicationUserTest {

    @Test
    void newly_created_user_should_have_default_config() {
        var user = new ApplicationUser();
        var defaultConfig = UserConfiguration.defaultConfiguration();

        assertThat(user.getUserConfiguration()).isEqualTo(defaultConfig);
    }
}