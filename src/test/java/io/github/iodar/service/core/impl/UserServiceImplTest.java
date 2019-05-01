package io.github.iodar.service.core.impl;

import io.github.iodar.DBCleanupService;
import io.github.iodar.TransactionlessTestEntityManager;
import io.github.iodar.persistence.entities.UserDbo;
import io.github.iodar.service.core.model.User;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.bouncycastle.util.CollectionStore;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Inject
    private UserServiceImpl userServiceImpl;

    @Inject
    private TransactionlessTestEntityManager transactionlessTestEntityManager;

    @Inject
    private DBCleanupService dbCleanupService;

    @BeforeEach
    void setUp() {
        dbCleanupService.cleanupDb();
    }

    @Test
    void findAll() {
        // prep
        final UserDbo user = new UserDbo().setVorname("Peter");
        final UserDbo user1 = new UserDbo().setVorname("Max");
        final Collection<UserDbo> users = asList(user, user1);
        persistAllOfAndFlush(users);

        // Act
        final List<User> usersInDatabase = userServiceImpl.findAll();

        // Assert
        MatcherAssert.assertThat(usersInDatabase, hasSize(users.size()));
    }

    @Test
    void findByNachname() {
        // prep
        final UserDbo user = new UserDbo().setNachname("Müller");
        final UserDbo user1 = new UserDbo().setNachname("müller");
        final UserDbo user2 = new UserDbo().setNachname("meier");
        final Collection<UserDbo> users = asList(user, user1, user2);
        persistAllOfAndFlush(users);

        // Act
        final List<User> usersInDatabase = userServiceImpl.findByNachname("Müller");

        // Assert
        Assertions.assertAll(
                () -> MatcherAssert.assertThat(usersInDatabase, hasSize(1)),
                () -> MatcherAssert.assertThat(usersInDatabase.get(0).getNachname(), is("Müller"))
        );
    }

    @Test
    void findByVorname() {
        // prep
        final UserDbo user = new UserDbo().setVorname("Max");
        final UserDbo user1 = new UserDbo().setVorname("max");
        final UserDbo user2 = new UserDbo().setNachname("Max")
                .setVorname("Peter");
        final Collection<UserDbo> users = asList(user, user1, user2);
        persistAllOfAndFlush(users);

        // Act
        final List<User> usersInDatabase = userServiceImpl.findByVorname("Max");

        // Assert
        Assertions.assertAll(
                () -> MatcherAssert.assertThat(usersInDatabase, hasSize(1)),
                () -> MatcherAssert.assertThat(usersInDatabase.get(0).getVorname(), is("Max"))
        );

    }

    @Test
    void findByNachnameAndVorname() {
        // prep
        final UserDbo user = new UserDbo()
                .setVorname("Max")
                .setNachname("Meier");
        final UserDbo user1 = new UserDbo()
                .setVorname("max")
                .setNachname("meier");
        final UserDbo user2 = new UserDbo()
                .setNachname("max")
                .setVorname("Meier");
        final Collection<UserDbo> users = asList(user, user1, user2);
        persistAllOfAndFlush(users);

        // Act
        final List<User> usersInDatabase = userServiceImpl.findByNachnameAndVorname("Meier", "Max");

        // Assert
        Assertions.assertAll(
                () -> MatcherAssert.assertThat(usersInDatabase, hasSize(1)),
                () -> MatcherAssert.assertThat(usersInDatabase.get(0).getVorname(), is("Max")),
                () -> MatcherAssert.assertThat(usersInDatabase.get(0).getNachname(), is("Meier"))
        );
    }

    private <T> void persistAllOfAndFlush(final Collection<T> collectionOfEntities) {
        collectionOfEntities.forEach(transactionlessTestEntityManager::persistAndFlush);
    }
}