package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final AddExpenseService addExpenseService;

    public ExpenseController(AddExpenseService addExpenseService) {
        this.addExpenseService = addExpenseService;
    }

    @PostMapping
    public ResponseEntity<MonthlyExpenses> add(@RequestBody ExpenseWriteModel toAdd, Principal principal) {
        MonthlyExpenses result = addExpenseService.add(toAdd.getExpense(), toAdd.getDate(), principal.getName());
        return ResponseEntity.ok(result);
    }
}
