package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;
import xyz.stasiak.cobudgetbackend.moneyentry.EntryBadRequestException;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Document("Expenses")
public class MonthlyExpenses {

    @Id
    private String id;

    private String username;
    private MonthAndYearDate date;
    private List<Expense> expenses;
    private BigDecimal sumOfExpenses;

    public MonthlyExpenses() {
    }

    public MonthlyExpenses(String username, MonthAndYearDate date) {
        this.username = username;
        this.date = date;
        expenses = new ArrayList<>();
        sumOfExpenses = BigDecimal.ZERO;
    }

    public MonthlyExpenses(String username, MonthAndYearDate date, List<Expense> expenses, BigDecimal sumOfExpenses) {
        this.username = username;
        this.date = date;
        this.expenses = expenses;
        this.sumOfExpenses = sumOfExpenses;
    }

    public MonthlyExpenses(String id, String username, MonthAndYearDate date, List<Expense> expenses,
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

    public List<Expense> getExpenses() {
        return expenses;
    }

    public BigDecimal getSumOfExpenses() {
        return sumOfExpenses;
    }

    public void addExpense(Expense expense) {
        if (expense.getDay() <= 0 || expense.getDay() > getMonth().length(isLeapYear())) {
            throw new EntryBadRequestException("day field is out of range for month " + getMonth().name());
        }
        sumOfExpenses = sumOfExpenses.add(expense.getAmount());
        expenses.add(expense);
    }

    private boolean isLeapYear() {
        boolean isLeap;
        if (getYear() % 4 == 0) {
            if (getYear() % 100 == 0) {
                isLeap = getYear() % 400 == 0;
            } else {
                isLeap = true;
            }
        } else {
            isLeap = false;
        }
        return isLeap;
    }
}
