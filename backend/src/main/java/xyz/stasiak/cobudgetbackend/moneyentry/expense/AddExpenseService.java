package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import org.springframework.transaction.annotation.Transactional;
import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;

public class AddExpenseService {

    private final MonthlyExpensesRepository repository;

    public AddExpenseService(MonthlyExpensesRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public MonthlyExpenses add(Expense expense, MonthAndYearDate date, String username) {
        MonthlyExpenses expenses = repository.findByUsernameAndDate(username, date)
                                             .orElse(new MonthlyExpenses(username, date));
        expenses.addExpense(expense);
        return repository.save(expenses); // I have to call save() to test it with mock
    }
}
