package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;
import xyz.stasiak.cobudgetbackend.validation.CheckDateFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseWriteModel {

    @CheckDateFormat(pattern = "uuuu-MM-dd", message = "date is invalid")
    private final String date;

    @DecimalMin(value = "0.0", message = "amount can't be negative")
    @Digits(integer = 15, fraction = 2, message = "amount can have max 2 decimal places")
    private final BigDecimal amount;

    @NotBlank(message = "category is mandatory")
    private final String category;

    @NotBlank(message = "subcategory is mandatory")
    private final String subcategory;
    private final String comment;

    public ExpenseWriteModel(String date, BigDecimal amount, String category, String subcategory, String comment) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.subcategory = subcategory;
        this.comment = comment;
    }

    public MonthAndYearDate getDate() {
        var localDate = LocalDate.parse(date);
        return new MonthAndYearDate(localDate.getMonth(), localDate.getYear());
    }

    public Expense getExpense() {
        var localDate = LocalDate.parse(date);
        return new Expense(localDate.getDayOfMonth(), amount, category, subcategory, comment);
    }
}
