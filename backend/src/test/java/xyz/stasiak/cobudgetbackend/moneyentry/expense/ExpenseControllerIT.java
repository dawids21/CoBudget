package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import xyz.stasiak.cobudgetbackend.security.SecurityConfig;
import xyz.stasiak.cobudgetbackend.security.SecurityProperties;
import xyz.stasiak.cobudgetbackend.security.WebSecurity;
import xyz.stasiak.cobudgetbackend.users.ApplicationUserRepository;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("integration")
@Testcontainers
@ContextConfiguration(classes = {ExpenseControllerIT.RestAssuredConfig.class, ExpensesConfig.class,
                                 SecurityConfig.class, WebSecurity.class, SecurityProperties.class})
@WebMvcTest
class ExpenseControllerIT {

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    private String testExpense() {
        return """
                 {
                     "expense": {
                         "day": 5,
                         "amount": 20,
                         "category": "fun",
                         "subcategory": "cinema"
                     },
                     "date": {
                         "month": "OCTOBER",
                         "year": 2020
                     }
                 }
                                  """;
    }

    @Configuration(proxyBeanMethods = false)
    @Profile("integration")
    static class RestAssuredConfig {

        @Bean
        ApplicationUserRepository applicationUserRepository() {
            return Mockito.mock(ApplicationUserRepository.class);
        }

        @Bean
        MonthlyExpensesRepository monthlyExpensesRepository() {
            return new TestExpenseConfig().testMonthlyExpensesRepository();
        }

        @Bean
        ExpenseController expenseController(AddExpenseService service) {
            return new ExpenseController(service);
        }
    }

}