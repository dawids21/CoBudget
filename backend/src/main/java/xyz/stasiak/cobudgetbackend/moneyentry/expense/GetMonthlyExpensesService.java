package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;
import xyz.stasiak.cobudgetbackend.moneyentry.EntryNotFound;

public class GetMonthlyExpensesService {

    private final MonthlyExpensesRepository repository;

    public GetMonthlyExpensesService(MonthlyExpensesRepository repository) {
        this.repository = repository;
    }

    public MonthlyExpenses getExpenses(String username, MonthAndYearDate date) {
        return repository.findByUsernameAndDate(username, date)
                         .orElseThrow(() -> new EntryNotFound(
                                  "monthly expenses for " + date.getMonth() + " " + date.getYear() + " not found"));
    }
}
