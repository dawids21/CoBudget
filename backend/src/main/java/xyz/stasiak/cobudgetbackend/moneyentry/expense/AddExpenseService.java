package xyz.stasiak.cobudgetbackend.moneyentry.expense;

public class AddExpenseService {

    private final MonthlyExpensesRepository repository;

    public AddExpenseService(MonthlyExpensesRepository repository) {
        this.repository = repository;
    }

    public Expense add(Expense expense, String username) {
        //TODO implement add
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
