package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AddExpenseServiceTest {

    private AddExpenseService addExpenseService;
    private MonthlyExpensesRepository monthlyExpensesRepository;

    @BeforeEach
    void setUp() {
        monthlyExpensesRepository = new TestExpenseConfig().testMonthlyExpensesRepository();
        addExpenseService = new TestExpenseConfig().tesAddExpenseService(monthlyExpensesRepository);
    }

    @Test
    void add_expense_to_current_month_monthly_expenses_entity_for_given_user() {
        var expense = testExpense();
        var user = TestExpenseConfig.TEST_USER;
        var currentDate = TestExpenseConfig.CURRENT_DATE;

        addExpenseService.add(expense, currentDate, user.getEmail());

        var argument = ArgumentCaptor.forClass(MonthlyExpenses.class);
        var monthlyExpenses = getSavedMonthlyExpenses();
        assertThat(monthlyExpenses.getExpenses()).contains(expense);
    }

    @Test
    void add_expense_to_monthly_expense_with_current_date() {
        var expense = testExpense();
        var user = TestExpenseConfig.TEST_USER;
        var currentDate = TestExpenseConfig.CURRENT_DATE;

        addExpenseService.add(expense, currentDate, user.getEmail());

        MonthlyExpenses monthlyExpenses = getSavedMonthlyExpenses();
        assertThat(monthlyExpenses.getMonth()).isEqualTo(currentDate.getMonth());
        assertThat(monthlyExpenses.getYear()).isEqualTo(currentDate.getYear());
    }

    @Test
    void add_expense_value_to_sum_of_expenses_in_monthly_expense() {
        var expense = testExpense();
        var user = TestExpenseConfig.TEST_USER;
        var currentDate = TestExpenseConfig.CURRENT_DATE;
        var sumBefore = TestExpenseConfig.TEST_MONTHLY_EXPENSES.getSumOfExpenses();

        addExpenseService.add(expense, currentDate, user.getEmail());

        MonthlyExpenses monthlyExpenses = getSavedMonthlyExpenses();
        assertThat(monthlyExpenses.getSumOfExpenses()).isEqualTo(sumBefore.add(expense.getAmount()));
    }

    @Test
    void create_monthly_expenses_if_doesnt_exist_yet() {
        //TODO implement create_monthly_expenses_if_doesnt_exist_yet
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void reject_adding_when_day_is_out_of_range_for_given_month() {
        //TODO implement reject_adding_when_day_is_out_of_range_for_given_month
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Test
    void reject_adding_when_amount_is_negative() {
        //TODO implement reject_adding_when_amount_is_negative
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private Expense testExpense() {
        return new Expense(3, BigDecimal.valueOf(5), "", "");
    }

    private MonthlyExpenses getSavedMonthlyExpenses() {
        var argument = ArgumentCaptor.forClass(MonthlyExpenses.class);
        verify(monthlyExpensesRepository).save(argument.capture());
        return argument.getValue();
    }
}