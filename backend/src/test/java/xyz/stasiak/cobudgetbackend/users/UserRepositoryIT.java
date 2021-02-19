package xyz.stasiak.cobudgetbackend.users;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@Testcontainers
@ActiveProfiles("integration")
class UserRepositoryIT {

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
    private UserRepository userRepository;

    @Test
    void find_user_with_given_id() {
        var user = exampleUser();
        userRepository.save(user);

        var found = userRepository.findByEmail(user.getEmail());

        assertThat(found).isNotEmpty();
    }

    private User exampleUser() {
        return new User("abc@def.com", "Power", "John");
    }
}