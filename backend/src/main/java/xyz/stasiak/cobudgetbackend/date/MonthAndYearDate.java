package xyz.stasiak.cobudgetbackend.date;

public class MonthAndYearDate {

    private final int month;
    private final int year;

    public MonthAndYearDate(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
