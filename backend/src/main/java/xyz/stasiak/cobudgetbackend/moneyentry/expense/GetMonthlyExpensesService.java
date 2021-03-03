package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;

public class GetMonthlyExpensesService {

    private final MonthlyExpensesRepository repository;

    public GetMonthlyExpensesService(MonthlyExpensesRepository repository) {
        this.repository = repository;
    }

    public MonthlyExpenses getExpenses(String username, MonthAndYearDate date) {
        return repository.findByUsernameAndDate(username, date)
                         .get();
    }
}
