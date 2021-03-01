package xyz.stasiak.cobudgetbackend.users.config;

import java.util.Objects;

public class UserConfiguration {

    private final AddExpenseNotificationConfig addExpenseNotificationConfig = new AddExpenseNotificationConfig();

    public static UserConfiguration defaultConfiguration() {
        var config = new UserConfiguration();
        config.addExpenseNotificationConfig.setEnabled(true);
        return config;
    }

    UserConfiguration() {
    }

    public boolean isAddExpenseNotificationEnabled() {
        return addExpenseNotificationConfig.isEnabled();
    }

    public void toggleAddExpenseNotification() {
        addExpenseNotificationConfig.setEnabled(!addExpenseNotificationConfig.isEnabled());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserConfiguration that = (UserConfiguration) o;
        return Objects.equals(addExpenseNotificationConfig, that.addExpenseNotificationConfig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addExpenseNotificationConfig);
    }
}
