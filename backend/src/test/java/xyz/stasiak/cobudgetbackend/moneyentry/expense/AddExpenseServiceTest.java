package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;
import xyz.stasiak.cobudgetbackend.moneyentry.EntryException;

import java.math.BigDecimal;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        var expense = testExpense();
        var user = TestExpenseConfig.TEST_USER;
        var currentDate = new MonthAndYearDate(Month.MAY, 2019);

        addExpenseService.add(expense, currentDate, user.getEmail());

        MonthlyExpenses monthlyExpenses = getSavedMonthlyExpenses();
        assertThat(monthlyExpenses.getExpenses()).hasSize(1);
        assertThat(monthlyExpenses.getMonth()).isEqualTo(currentDate.getMonth());
        assertThat(monthlyExpenses.getYear()).isEqualTo(currentDate.getYear());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    void throws_exception_when_creating_expense_with_days_out_of_month_range(int day) {
        var user = TestExpenseConfig.TEST_USER;
        var currentDate = TestExpenseConfig.CURRENT_DATE;
        var expense = new Expense(day, BigDecimal.ZERO, "", "");

        assertThatThrownBy(() -> addExpenseService.add(expense, currentDate, user.getEmail())).isInstanceOf(
                 EntryException.class)
                                                                                              .hasMessageContaining(
                                                                                                       "day");
    }

    @Test
    void reject_adding_when_amount_is_negative() {
        assertThatThrownBy(() -> new Expense(3, BigDecimal.valueOf(-1), "", "")).isInstanceOf(EntryException.class)
                                                                                .hasMessageContaining("amount");
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