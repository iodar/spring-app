package io.github.iodar.persistence.converter;

import io.github.iodar.persistence.entities.UserDbo;
import io.github.iodar.service.core.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class UserDboConverterITest {

    @Inject
    private UserDboConverter userDboConverter;

    @Test
    @DisplayName("Konvertierung Model => Dbo sollte vollständiges Dbo-Objekt ohne Id liefern")
    void convertToDbo() {
        //prep
        final User user = User.builder()
                .vorname("Peter")
                .nachname("Müller")
                .geburtsdatum(LocalDate.of(2000, 1, 1))
                .build();

        // act
        final UserDbo userDbo = userDboConverter.convertToDbo(user);

        // assert
        assertAll(
                () -> assertThat(userDbo.getVorname(), is(user.getVorname())),
                () -> assertThat(userDbo.getNachname(), is(user.getNachname())),
                () -> assertThat(userDbo.getGeburtsdatum(), is(user.getGeburtsdatum())),
                () -> assertThat(userDbo.getId(), is(nullValue()))
        );
    }

    @Test
    @DisplayName("Konvertierung Model => Dbo sollte null liefern wenn null übergeben wurde")
    void convertToDboWithParamNull() {
        // act
        final UserDbo userDbo = userDboConverter.convertToDbo(null);

        // assert
        assertThat(userDbo, is(nullValue()));
    }

    @Test
    @DisplayName("Konvertierung Dbo => Model sollte vollständiges Model-Objekt mit Id liefern")
    void convertToModel() {
        //prep
        final UserDbo userDbo = UserDbo.builder()
                .id(1L)
                .vorname("Peter")
                .nachname("Müller")
                .geburtsdatum(LocalDate.of(2000, 1, 1))
                .build();

        // act
        final User user = userDboConverter.convertToModel(userDbo);

        // assert
        assertAll(
                () -> assertThat(user.getVorname(), is(userDbo.getVorname())),
                () -> assertThat(user.getNachname(), is(userDbo.getNachname())),
                () -> assertThat(user.getGeburtsdatum(), is(userDbo.getGeburtsdatum())),
                () -> assertThat(user.getId(), is(userDbo.getId()))
        );
    }

    @Test
    @DisplayName("Konvertierung Dbo => Model sollte null liefern wenn null übergeben wurde")
    void convertToModelWithParamNull() {
        // act
        final User user = userDboConverter.convertToModel(null);

        // assert
        assertThat(user, is(nullValue()));
    }
}