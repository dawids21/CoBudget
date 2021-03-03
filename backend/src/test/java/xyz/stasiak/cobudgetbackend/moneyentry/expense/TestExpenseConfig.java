package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;
import xyz.stasiak.cobudgetbackend.users.ApplicationUser;

import java.math.BigDecimal;
import java.time.Month;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestExpenseConfig extends ExpensesConfig {

    final String TEST_USERNAME = "abc@abc.com";
    final ApplicationUser TEST_USER = new ApplicationUser(TEST_USERNAME, "pass", "John");
    final MonthAndYearDate CURRENT_DATE = new MonthAndYearDate(Month.FEBRUARY, 2020);
    final MonthlyExpenses TEST_MONTHLY_EXPENSES =
             new MonthlyExpenses("1", TEST_USER.getEmail(), CURRENT_DATE, new HashSet<>(), BigDecimal.ZERO);

    MonthlyExpensesRepository testMonthlyExpensesRepository() {
        var repository = mock(MonthlyExpensesRepository.class);

        when(repository.findByUsernameAndDate(anyString(), any())).then(invocation -> {

            var username = invocation.getArgument(0, String.class);
            var date = invocation.getArgument(1, MonthAndYearDate.class);

            if (username.equals(TEST_USER.getEmail()) && date.equals(CURRENT_DATE)) {
                return Optional.of(TEST_MONTHLY_EXPENSES);
            } else {
                return Optional.empty();
            }

        });

        when(repository.save(any())).then(invocation -> invocation.getArgument(0, MonthlyExpenses.class));

        return repository;
    }

    AddExpenseService testAddExpenseService() {
        return testAddExpenseService(testMonthlyExpensesRepository());
    }

    AddExpenseService testAddExpenseService(MonthlyExpensesRepository repository) {
        return addExpenseService(repository);
    }
}
