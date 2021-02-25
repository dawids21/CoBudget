package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;

public class ExpenseWriteModel {

    private final MonthAndYearDate date;
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
