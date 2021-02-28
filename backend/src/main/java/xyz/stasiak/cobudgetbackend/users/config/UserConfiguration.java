package xyz.stasiak.cobudgetbackend.users.config;

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
}
