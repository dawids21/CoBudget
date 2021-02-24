package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

interface MongoMonthlyExpensesRepository extends MonthlyExpensesRepository, MongoRepository<MonthlyExpenses, String> {

    @Override
    Optional<MonthlyExpenses> findByUsername(String username);

    @Override
    Optional<MonthlyExpenses> findByUsernameAndMonthAndYear(String username, int month, int year);
}
