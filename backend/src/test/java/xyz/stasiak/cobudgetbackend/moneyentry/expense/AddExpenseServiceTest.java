package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AddExpenseServiceTest {

    private final AddExpenseService addExpenseService = new TestExpenseConfig().addExpenseService();
}