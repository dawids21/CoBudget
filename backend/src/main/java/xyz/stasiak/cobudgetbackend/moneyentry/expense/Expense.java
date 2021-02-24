package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import java.math.BigDecimal;

public class Expense {

    private final int day;
    private final BigDecimal amount;
    private final String category;
    private final String subcategory;

    public Expense(int day, BigDecimal amount, String category, String subcategory) {
        this.day = day;
        this.amount = amount;
        this.category = category;
        this.subcategory = subcategory;
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
