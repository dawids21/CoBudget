package xyz.stasiak.cobudgetbackend.moneyentry.expense;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.junit.jupiter.Testcontainers;
import xyz.stasiak.cobudgetbackend.security.SecurityConfig;
import xyz.stasiak.cobudgetbackend.security.SecurityProperties;
import xyz.stasiak.cobudgetbackend.security.WebSecurity;
import xyz.stasiak.cobudgetbackend.users.ApplicationUserRepository;

import java.math.BigDecimal;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @WithMockUser(username = TestExpenseConfig.TEST_USERNAME)
    void hide_password_field_after_user_signup(@Autowired WebApplicationContext context) {
        var result = given().webAppContextSetup(context)
                            .body(testExpenseWriteModel())
                            .contentType(ContentType.JSON)
                            .when()
                            .post("/expense")
                            .then()
                            .statusCode(200)
                            .body("expenses.size()", Matchers.equalTo(1))
                            .extract()
                            .jsonPath()
                            .getObject("expenses[0]", Expense.class);
        assertThat(result).isEqualTo(testExpense());
    }

    private Expense testExpense() {
        return new Expense(5, BigDecimal.valueOf(20.34), "fun", "cinema");
    }

    private String testExpenseWriteModel() {
        return """
                 {
                     "expense": {
                         "day": 5,
                         "amount": 20.34,
                         "category": "fun",
                         "subcategory": "cinema"
                     },
                     "date": {
                         "month": "FEBRUARY",
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