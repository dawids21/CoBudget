package xyz.stasiak.cobudgetbackend.expense;

import org.bson.types.Decimal128;

public class Expense {

    private int day;
    private Decimal128 amount;
    private String category;
    private String subcategory;

    public Expense() {
    }

    public Expense(int day, Decimal128 amount, String category, String subcategory) {
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

    public Decimal128 getAmount() {
        return amount;
    }

    public void setAmount(Decimal128 amount) {
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
