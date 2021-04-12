package xyz.stasiak.cobudgetbackend.users.config;

import java.util.Objects;

public class UserConfiguration {

    private EntryNotification entryNotification = new EntryNotification();

    public static UserConfiguration defaultConfiguration() {
        var config = new UserConfiguration();
        config.entryNotification.setEnabled(true);
        return config;
    }

    public UserConfiguration() {
    }

    public EntryNotification getEntryNotification() {
        return entryNotification;
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
        return Objects.equals(entryNotification, that.entryNotification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryNotification);
    }
}
