package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GetMonthlyExpensesServiceTest {

    private GetMonthlyExpensesService service;

    @BeforeEach
    void setUp() {
        service = new GetMonthlyExpensesService(new TestExpenseConfig().testMonthlyExpensesRepository());
    }

    @Test
    void get_monthly_expenses_for_given_user_and_date() {
        MonthlyExpenses expenses =
                 service.getExpenses(TestExpenseConfig.TEST_USERNAME, new TestExpenseConfig().CURRENT_DATE);
        assertThat(expenses.getMonth()).isEqualTo(new TestExpenseConfig().CURRENT_DATE.getMonth());
        assertThat(expenses.getYear()).isEqualTo(new TestExpenseConfig().CURRENT_DATE.getYear());
        assertThat(expenses.getUsername()).isEqualTo(TestExpenseConfig.TEST_USERNAME);
    }

    @Test
    void throw_exception_when_expenses_not_found() {
        //TODO implement throw_exception_when_expenses_not_found
        throw new UnsupportedOperationException("Not implemented yet");
    }
}