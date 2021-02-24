package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Set;

@Document("Expenses")
public class MonthlyExpenses {

    @Id
    private String id;

    private String username;
    private MonthAndYearDate date;
    private Set<Expense> expenses;
    private BigDecimal sumOfExpenses;

    public MonthlyExpenses() {
    }

    public MonthlyExpenses(String username, MonthAndYearDate date) {
        this.username = username;
        this.date = date;
        expenses = Set.of();
        sumOfExpenses = BigDecimal.ZERO;
    }

    public MonthlyExpenses(String username, MonthAndYearDate date, Set<Expense> expenses, BigDecimal sumOfExpenses) {
        this.username = username;
        this.date = date;
        this.expenses = expenses;
        this.sumOfExpenses = sumOfExpenses;
    }

    public MonthlyExpenses(String id, String username, MonthAndYearDate date, Set<Expense> expenses,
                           BigDecimal sumOfExpenses) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.expenses = expenses;
        this.sumOfExpenses = sumOfExpenses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Month getMonth() {
        return date.getMonth();
    }

    public int getYear() {
        return date.getYear();
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public BigDecimal getSumOfExpenses() {
        return sumOfExpenses;
    }

    public void addToSumOfExpenses(BigDecimal toAdd) {
        sumOfExpenses = sumOfExpenses.add(toAdd);
    }

    public void subtractToSumOfExpenses(BigDecimal toSubtract) {
        sumOfExpenses = sumOfExpenses.subtract(toSubtract);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
}
