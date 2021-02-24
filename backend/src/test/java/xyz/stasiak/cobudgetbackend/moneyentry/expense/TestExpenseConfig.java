package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import xyz.stasiak.cobudgetbackend.users.ApplicationUser;

import static org.mockito.Mockito.mock;

class TestExpenseConfig extends ExpensesConfig {

    static final ApplicationUser TEST_USER = new ApplicationUser("1", "abc@abc.com", "pass", "John");

    MonthlyExpensesRepository testMonthlyExpensesRepository() {
        //TODO write logic
        return mock(MonthlyExpensesRepository.class);
    }

    AddExpenseService testAddExpenseService() {
        return tesAddExpenseService(testMonthlyExpensesRepository());
    }

    AddExpenseService tesAddExpenseService(MonthlyExpensesRepository repository) {
        return addExpenseService(repository);
    }
}
