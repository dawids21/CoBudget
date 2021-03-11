package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;
import xyz.stasiak.cobudgetbackend.validation.CheckDateFormat;

import javax.validation.Valid;
import java.time.LocalDate;

public class ExpenseWriteModel {

    @CheckDateFormat(pattern = "uuuu-MM-dd", message = "Date is invalid")
    private final String date;

    @Valid
    private final Expense expense;

    public ExpenseWriteModel(String date, Expense expense) {
        this.date = date;
        this.expense = expense;
    }

    public MonthAndYearDate getDate() {
        var localDate = LocalDate.parse(date);
        return new MonthAndYearDate(localDate.getMonth(), localDate.getYear());
    }

    public Expense getExpense() {
        return expense;
    }
}
