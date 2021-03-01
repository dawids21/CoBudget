package xyz.stasiak.cobudgetbackend.users.config;

import java.util.Objects;

public class UserConfiguration {

    private AddExpenseNotificationConfig addExpenseNotificationConfig = new AddExpenseNotificationConfig();

    public static UserConfiguration defaultConfiguration() {
        var config = new UserConfiguration();
        config.addExpenseNotificationConfig.setEnabled(true);
        return config;
    }

    public UserConfiguration() {
    }

    public AddExpenseNotificationConfig getAddExpenseNotificationConfig() {
        return addExpenseNotificationConfig;
    }

    public void setAddExpenseNotificationConfig(AddExpenseNotificationConfig addExpenseNotificationConfig) {
        this.addExpenseNotificationConfig = addExpenseNotificationConfig;
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
