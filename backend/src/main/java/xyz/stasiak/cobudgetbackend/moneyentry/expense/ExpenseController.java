package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.stasiak.cobudgetbackend.date.MonthAndYearDate;
import xyz.stasiak.cobudgetbackend.moneyentry.EntryException;
import xyz.stasiak.cobudgetbackend.validation.ValidationExceptionProcessing;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/expense")
@ValidationExceptionProcessing
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
    public ResponseEntity<MonthlyExpenses> getExpenses(@Valid @RequestBody MonthAndYearDate date, Principal principal) {
        return ResponseEntity.ok(getMonthlyExpensesService.getExpenses(principal.getName(), date));
    }

    @ExceptionHandler(EntryException.class)
    public ResponseEntity<String> handleEntryException(EntryException e) {
        return ResponseEntity.badRequest()
                             .body(e.getMessage());
    }
}
