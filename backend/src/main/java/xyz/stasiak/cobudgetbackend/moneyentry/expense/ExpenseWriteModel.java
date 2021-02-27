package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;

import javax.validation.Valid;

public class ExpenseWriteModel {

    @Valid
    private final MonthAndYearDate date;

    @Valid
    private final Expense expense;

    public ExpenseWriteModel(MonthAndYearDate date, Expense expense) {
        this.date = date;
        this.expense = expense;
    }

    public MonthAndYearDate getDate() {
        return date;
    }

    public Expense getExpense() {
        return expense;
    }
}
