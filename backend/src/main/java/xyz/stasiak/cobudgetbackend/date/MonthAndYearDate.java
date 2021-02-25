package xyz.stasiak.cobudgetbackend.date;

import java.time.Month;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MonthAndYearDate that = (MonthAndYearDate) o;
        return getYear() == that.getYear() && getMonth() == that.getMonth();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMonth(), getYear());
    }
}
