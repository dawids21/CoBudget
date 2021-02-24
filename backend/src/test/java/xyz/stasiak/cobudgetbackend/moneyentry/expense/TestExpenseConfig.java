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

    static final ApplicationUser TEST_USER = new ApplicationUser("1", "abc@abc.com", "pass", "John");
    static final MonthAndYearDate CURRENT_DATE = new MonthAndYearDate(Month.FEBRUARY, 2020);

    MonthlyExpensesRepository testMonthlyExpensesRepository() {
        var repository = mock(MonthlyExpensesRepository.class);
        when(repository.findByUsernameAndDate(anyString(), any())).then(invocation -> {
            var username = invocation.getArgument(0, String.class);
            if (username.equals(TEST_USER.getEmail())) {
                return Optional.of(new MonthlyExpenses("1", TEST_USER.getEmail(), CURRENT_DATE, new HashSet<>(),
                                                       BigDecimal.ZERO));
            } else {
                return Optional.empty();
            }
        });
        return repository;
    }

    AddExpenseService testAddExpenseService() {
        return tesAddExpenseService(testMonthlyExpensesRepository());
    }

    AddExpenseService tesAddExpenseService(MonthlyExpensesRepository repository) {
        return addExpenseService(repository);
    }
}
