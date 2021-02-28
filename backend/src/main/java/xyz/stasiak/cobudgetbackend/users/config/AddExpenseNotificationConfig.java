package xyz.stasiak.cobudgetbackend.users.config;

public class AddExpenseNotificationConfig {

    private boolean enabled;

    public AddExpenseNotificationConfig() {
    }

    public AddExpenseNotificationConfig(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
