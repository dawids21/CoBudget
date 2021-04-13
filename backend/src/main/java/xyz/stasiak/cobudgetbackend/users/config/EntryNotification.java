package xyz.stasiak.cobudgetbackend.users.config;

import java.util.Objects;

public class EntryNotification {

    private boolean enabled;

    public EntryNotification() {
    }

    public EntryNotification(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntryNotification that = (EntryNotification) o;
        return isEnabled() == that.isEnabled();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEnabled());
    }
}
