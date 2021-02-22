package xyz.stasiak.cobudgetbackend.expense;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import xyz.stasiak.cobudgetbackend.users.ApplicationUser;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@Testcontainers
@ActiveProfiles("integration")
class MonthlyExpensesRepositoryIT {

    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeAll
    static void setUpAll() {
        mongoDBContainer.start();
    }

    @AfterAll
    static void tearDownAll() {
        if (!mongoDBContainer.isShouldBeReused()) {
            mongoDBContainer.stop();
        }
    }

    @Autowired
    private MonthlyExpensesRepository monthlyExpensesRepository;

    @Test
    void return_user_monthly_expenses_for_given_month_and_year() {
        var user = new ApplicationUser("abc@def.com", "", "");
        var anotherUser = new ApplicationUser("def@efg.com", "", "");
        monthlyExpensesRepository.save(exampleMonthlyExpenses(user, 2, 2021));
        monthlyExpensesRepository.save(exampleMonthlyExpenses(anotherUser, 2, 2021));
        monthlyExpensesRepository.save(exampleMonthlyExpenses(user, 1, 2021));
        monthlyExpensesRepository.save(exampleMonthlyExpenses(user, 2, 2020));

        Optional<MonthlyExpenses> result =
                 monthlyExpensesRepository.findByUsernameAndMonthAndYear(user.getEmail(), 2, 2021);
        assertThat(result).isNotEmpty();
        assertThat(result.get()
                         .getUsername()).isEqualTo(user.getEmail());
    }

    private MonthlyExpenses exampleMonthlyExpenses(ApplicationUser applicationUser, int month, int year) {
        var expenses = Set.of(new Expense(10, new BigDecimal("10.23"), "food", "for home"),
                              new Expense(2, new BigDecimal("18.23"), "hygiene", "chemistry"),
                              new Expense(1, new BigDecimal(20), "fun", "swimming pool"));
        var sum = expenses.stream()
                          .map(Expense::getAmount)
                          .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new MonthlyExpenses(applicationUser.getEmail(), month, year, expenses, sum);
    }
}
