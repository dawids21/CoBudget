package xyz.stasiak.cobudgetbackend.users.config;

import java.time.LocalTime;

public class UserConfigurationWriteModel {

    private boolean entryNotificationEnable;
    private LocalTime entryNotificationTime;

    public UserConfigurationWriteModel(boolean entryNotificationEnable, LocalTime entryNotificationTime) {
        this.entryNotificationEnable = entryNotificationEnable;
        this.entryNotificationTime = entryNotificationTime;
    }

    public boolean isEntryNotificationEnable() {
        return entryNotificationEnable;
    }

    public void setEntryNotificationEnable(boolean entryNotificationEnable) {
        this.entryNotificationEnable = entryNotificationEnable;
    }

    public LocalTime getEntryNotificationTime() {
        return entryNotificationTime;
    }

    public void setEntryNotificationTime(LocalTime entryNotificationTime) {
        this.entryNotificationTime = entryNotificationTime;
    }
}
