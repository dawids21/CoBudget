package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;

import java.util.Optional;

interface MongoMonthlyExpensesRepository extends MonthlyExpensesRepository, MongoRepository<MonthlyExpenses, String> {

    @Override
    Optional<MonthlyExpenses> findByUsername(String username);

    @Override
    Optional<MonthlyExpenses> findByUsernameAndDate(String username, MonthAndYearDate date);
}
