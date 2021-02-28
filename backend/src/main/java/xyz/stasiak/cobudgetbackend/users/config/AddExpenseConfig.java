package xyz.stasiak.cobudgetbackend.users.config;

public class AddExpenseConfig {

    private boolean enabled;

    public AddExpenseConfig() {
    }

    public AddExpenseConfig(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
