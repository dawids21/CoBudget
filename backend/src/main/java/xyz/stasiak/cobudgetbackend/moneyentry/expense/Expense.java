package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import xyz.stasiak.cobudgetbackend.moneyentry.EntryException;

import java.math.BigDecimal;
import java.util.Objects;

public class Expense {
   
    private int day;
    private BigDecimal amount;
    private String category;
    private String subcategory;
    private String comment;

    public Expense() {
    }

    public Expense(int day, BigDecimal amount, String category, String subcategory) {
        this(day, amount, category, subcategory, null);
    }

    public Expense(int day, BigDecimal amount, String category, String subcategory, String comment) {
        this.day = day;
        this.amount = checkAmount(amount);
        this.category = category;
        this.subcategory = subcategory;
        this.comment = comment;
    }

    private BigDecimal checkAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new EntryException("amount can't be negative");
        }
        return amount;
    }

    public int getDay() {
        return day;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Expense expense = (Expense) o;
        return getDay() == expense.getDay() && Objects.equals(getAmount(), expense.getAmount()) &&
               Objects.equals(getCategory(), expense.getCategory()) &&
               Objects.equals(getSubcategory(), expense.getSubcategory()) &&
               Objects.equals(getComment(), expense.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDay(), getAmount(), getCategory(), getSubcategory(), getComment());
    }
}
