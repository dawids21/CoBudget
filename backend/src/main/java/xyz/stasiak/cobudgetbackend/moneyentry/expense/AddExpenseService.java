package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;

public class AddExpenseService {

    private final MonthlyExpensesRepository repository;

    public AddExpenseService(MonthlyExpensesRepository repository) {
        this.repository = repository;
    }

    public Expense add(Expense expense, MonthAndYearDate date, String username) {
        //TODO implement add
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
