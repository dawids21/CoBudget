package xyz.stasiak.cobudgetbackend.date;

import java.time.Month;

public class MonthAndYearDate {

    private final Month month;
    private final int year;

    public MonthAndYearDate(Month month, int year) {
        this.month = month;
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
