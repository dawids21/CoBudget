package xyz.stasiak.cobudgetbackend.users;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;
import xyz.stasiak.cobudgetbackend.security.SecurityConfig;
import xyz.stasiak.cobudgetbackend.security.SecurityProperties;
import xyz.stasiak.cobudgetbackend.security.WebSecurity;

import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@WebMvcTest
@ContextConfiguration(classes = {ApplicationUserControllerIT.RestAssuredConfig.class, SecurityConfig.class,
                                 WebSecurity.class, SecurityProperties.class})
@ActiveProfiles("integration")
class ApplicationUserControllerIT {

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void hide_password_field_after_user_signup(@Autowired WebApplicationContext context) {
        ApplicationUser testUser = testUser();
        given().webAppContextSetup(context)
               .body(testUserJson())
               .contentType(ContentType.JSON)
               .when()
               .post("/user/sign-up")
               .then()
               .statusCode(200)
               .body("email", Matchers.equalTo(testUser.getEmail()))
               .body("name", Matchers.equalTo(testUser.getName()))
               .body("", Matchers.not(Matchers.hasKey("password")));

    }

    private ApplicationUser testUser() {
        return new ApplicationUser("abc@def.com", "1234", "John");
    }

    private String testUserJson() {
        return """
                 {
                    "email": "abc@def.com",
                    "password": "1234",
                    "name": "John"
                 }
                 """;
    }

    @Configuration(proxyBeanMethods = false)
    @Profile("integration")
    static class RestAssuredConfig {

        @Bean
        ApplicationUserRepository applicationUserRepository() {
            var repository = Mockito.mock(ApplicationUserRepository.class);
            Mockito.when(repository.save(Mockito.any(ApplicationUser.class)))
                   .then(invocation -> {
                       ApplicationUser user = invocation.getArgument(0, ApplicationUser.class);
                       user.setId("123");
                       user.setPassword("$2a$10$hD0qs6lLCqm7myZvR8rYMOjB1SogzFx.Li5ZIFW/qTA.aziKTkqXO");
                       return user;
                   });
            Mockito.when(repository.findByEmail(Mockito.anyString()))
                   .thenReturn(Optional.of(new ApplicationUser("abc@def.com",
                                                               "$2a$10$hD0qs6lLCqm7myZvR8rYMOjB1SogzFx.Li5ZIFW/qTA.aziKTkqXO",
                                                               "John")));

            return repository;
        }

        @Bean
        ApplicationUserController applicationUserController(ApplicationUserRepository repository,
                                                            BCryptPasswordEncoder bCryptPasswordEncoder) {
            return new ApplicationUserController(repository, bCryptPasswordEncoder);
        }
    }

}

