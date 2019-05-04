package io.github.iodar.service.core.impl;

import io.github.iodar.DBCleanupService;
import io.github.iodar.TransactionlessTestEntityManager;
import io.github.iodar.persistence.entities.UserDbo;
import io.github.iodar.service.core.model.User;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@DisplayName("User Service")
@SpringBootTest
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
    @DisplayName("sollte alle Einträge liefern können")
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

    @DisplayName("sollte Einträge mit einem bestimmten Nachnamen finden können")
    @ParameterizedTest(name = "Einträge mit Nachname \"{0}\" sollte gefunden werden")
    @ValueSource(strings = {"Müller", "Meier-Beier", "von Hagedorn", "Sâínt-Louise"})
    void findByNachname(String nachname) {
        // prep
        final UserDbo user = new UserDbo().setNachname(nachname);
        final UserDbo user1 = new UserDbo().setNachname("müller");
        final UserDbo user2 = new UserDbo().setNachname("meier");
        final Collection<UserDbo> users = asList(user, user1, user2);
        persistAllOfAndFlush(users);

        // Act
        final List<User> usersInDatabase = userServiceImpl.findByNachname(nachname);

        // Assert
        Assertions.assertAll(
                () -> MatcherAssert.assertThat(usersInDatabase, hasSize(1)),
                () -> MatcherAssert.assertThat(usersInDatabase.get(0).getNachname(), is(nachname))
        );
    }

    @DisplayName("sollte Einträge mit einem bestimmten Vornamen finden können")
    @ParameterizedTest(name = "Einträge mit Vorame \"{0}\" sollten gefunden werden")
    @ValueSource(strings = {"Max", "Réne", "Hartmut Otto Emil", "Hans-Peter"})
    void findByVorname(String vorname) {
        // prep
        final UserDbo user = new UserDbo().setVorname(vorname);
        final UserDbo user1 = new UserDbo().setVorname("max");
        final UserDbo user2 = new UserDbo().setNachname("Max")
                .setVorname("Peter");
        final Collection<UserDbo> users = asList(user, user1, user2);
        persistAllOfAndFlush(users);

        // Act
        final List<User> usersInDatabase = userServiceImpl.findByVorname(vorname);

        // Assert
        Assertions.assertAll(
                () -> MatcherAssert.assertThat(usersInDatabase, hasSize(1)),
                () -> MatcherAssert.assertThat(usersInDatabase.get(0).getVorname(), is(vorname))
        );

    }

    @DisplayName("sollte Einträge mit Vor- und Nachnamen finden können")
    @ParameterizedTest(name = "Einträge mit Vorname \"{0}\" und Nachname \"{1}\" sollten gefunden werden")
    @CsvSource({
            "Meier, Müller",
            "Max-Peter, Müller",
            "Réne, Sâínt-Louise",
            "Hans Peter, Mäxchen",
            "Hans Peter, von Hagedorn"
    })
    void findByNachnameAndVorname(String vorname, String nachname) {
        // prep
        final UserDbo user = new UserDbo()
                .setVorname(vorname)
                .setNachname(nachname);
        final UserDbo user1 = new UserDbo()
                .setVorname("max")
                .setNachname("meier");
        final UserDbo user2 = new UserDbo()
                .setNachname("max")
                .setVorname("Meier");
        final Collection<UserDbo> users = asList(user, user1, user2);
        persistAllOfAndFlush(users);

        // Act
        final List<User> usersInDatabase = userServiceImpl.findByNachnameAndVorname(nachname, vorname);

        // Assert
        Assertions.assertAll(
                () -> MatcherAssert.assertThat(usersInDatabase, hasSize(1)),
                () -> MatcherAssert.assertThat(usersInDatabase.get(0).getVorname(), is(vorname)),
                () -> MatcherAssert.assertThat(usersInDatabase.get(0).getNachname(), is(nachname))
        );
    }

    private <T> void persistAllOfAndFlush(final Collection<T> collectionOfEntities) {
        collectionOfEntities.forEach(transactionlessTestEntityManager::persistAndFlush);
    }
}