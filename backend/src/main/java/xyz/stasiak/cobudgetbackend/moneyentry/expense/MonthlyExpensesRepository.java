package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;

import java.util.Optional;

public interface MonthlyExpensesRepository {

    MonthlyExpenses save(MonthlyExpenses toSave);

    Optional<MonthlyExpenses> findByUsername(String username);

    Optional<MonthlyExpenses> findByUsernameAndDate(String username, MonthAndYearDate date);
}
