package xyz.stasiak.cobudgetbackend.moneyentry;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Set;

@Document("Expenses")
public class MonthlyExpenses {

    @Id
    private String id;

    private String username;
    private int month;
    private int year;
    private Set<Expense> expenses;
    private BigDecimal sumOfExpenses;

    public MonthlyExpenses() {
    }

    public MonthlyExpenses(String username, int month, int year, Set<Expense> expenses, BigDecimal sumOfExpenses) {
        this.username = username;
        this.month = month;
        this.year = year;
        this.expenses = expenses;
        this.sumOfExpenses = sumOfExpenses;
    }

    public MonthlyExpenses(String id, String username, int month, int year, Set<Expense> expenses,
                           BigDecimal sumOfExpenses) {
        this.id = id;
        this.username = username;
        this.month = month;
        this.year = year;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }

    public BigDecimal getSumOfExpenses() {
        return sumOfExpenses;
    }

    public void setSumOfExpenses(BigDecimal sumOfExpenses) {
        this.sumOfExpenses = sumOfExpenses;
    }
}
