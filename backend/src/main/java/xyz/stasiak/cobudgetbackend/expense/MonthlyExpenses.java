package xyz.stasiak.cobudgetbackend.expense;

import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("Expenses")
public class MonthlyExpenses {

    @Id
    private String id;

    private String username;
    private int month;
    private int year;
    private Set<Expense> expenses;
    private Decimal128 sumOfExpenses;

    public MonthlyExpenses() {
    }

    public MonthlyExpenses(String username, int month, int year, Set<Expense> expenses, Decimal128 sumOfExpenses) {
        this.username = username;
        this.month = month;
        this.year = year;
        this.expenses = expenses;
        this.sumOfExpenses = sumOfExpenses;
    }

    public MonthlyExpenses(String id, String username, int month, int year, Set<Expense> expenses,
                           Decimal128 sumOfExpenses) {
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

    public Decimal128 getSumOfExpenses() {
        return sumOfExpenses;
    }

    public void setSumOfExpenses(Decimal128 sumOfExpenses) {
        this.sumOfExpenses = sumOfExpenses;
    }
}
