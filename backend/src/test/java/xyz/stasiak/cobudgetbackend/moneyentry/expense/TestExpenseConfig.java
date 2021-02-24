package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import static org.mockito.Mockito.mock;

class TestExpenseConfig extends ExpensesConfig {

    MonthlyExpensesRepository testMonthlyExpensesRepository() {
        //TODO write logic
        return mock(MonthlyExpensesRepository.class);
    }

    AddExpenseService testAddExpenseService() {
        return addExpenseService(testMonthlyExpensesRepository());
    }
}
