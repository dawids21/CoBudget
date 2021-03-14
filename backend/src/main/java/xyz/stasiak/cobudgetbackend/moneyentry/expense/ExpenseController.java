package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;
import xyz.stasiak.cobudgetbackend.validation.ValidationExceptionProcessing;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.security.Principal;
import java.time.Month;

@RestController
@RequestMapping("/expense")
@ValidationExceptionProcessing
@Validated
public class ExpenseController {

    private final AddExpenseService addExpenseService;
    private final GetMonthlyExpensesService getMonthlyExpensesService;

    public ExpenseController(AddExpenseService addExpenseService, GetMonthlyExpensesService getMonthlyExpensesService) {
        this.addExpenseService = addExpenseService;
        this.getMonthlyExpensesService = getMonthlyExpensesService;
    }

    @PostMapping
    public ResponseEntity<MonthlyExpenses> add(@Valid @RequestBody ExpenseWriteModel toAdd, Principal principal) {
        MonthlyExpenses result = addExpenseService.add(toAdd.getExpense(), toAdd.getDate(), principal.getName());
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<MonthlyExpenses> getExpenses(@RequestParam("month") @Min(1) @Max(12) int month,
                                                       @RequestParam("year") @Positive(message = "year must be greater than 0") int year,
                                                       Principal principal) {
        var date = new MonthAndYearDate(Month.of(month), year);
        return ResponseEntity.ok(getMonthlyExpensesService.getExpenses(principal.getName(), date));
    }
}
