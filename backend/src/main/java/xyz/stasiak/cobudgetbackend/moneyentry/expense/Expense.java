package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import xyz.stasiak.cobudgetbackend.moneyentry.EntryException;

import java.math.BigDecimal;

public class Expense {

    private final int day;
    private final BigDecimal amount;
    private final String category;
    private final String subcategory;

    public Expense(int day, BigDecimal amount, String category, String subcategory) {
        this.day = day;
        this.amount = checkAmount(amount);
        this.category = category;
        this.subcategory = subcategory;
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
}
