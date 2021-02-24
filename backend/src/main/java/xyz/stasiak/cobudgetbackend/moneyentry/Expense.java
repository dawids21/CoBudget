package xyz.stasiak.cobudgetbackend.moneyentry;

import java.math.BigDecimal;

public class Expense {

    private int day;
    private BigDecimal amount;
    private String category;
    private String subcategory;

    public Expense() {
    }

    public Expense(int day, BigDecimal amount, String category, String subcategory) {
        this.day = day;
        this.amount = amount;
        this.category = category;
        this.subcategory = subcategory;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
}
