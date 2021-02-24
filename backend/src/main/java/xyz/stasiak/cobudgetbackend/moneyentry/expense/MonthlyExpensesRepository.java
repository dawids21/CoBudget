package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import java.util.Optional;

public interface MonthlyExpensesRepository {

    MonthlyExpenses save(MonthlyExpenses toSave);

    Optional<MonthlyExpenses> findByUsername(String username);

    Optional<MonthlyExpenses> findByUsernameAndMonthAndYear(String username, int month, int year);
}
