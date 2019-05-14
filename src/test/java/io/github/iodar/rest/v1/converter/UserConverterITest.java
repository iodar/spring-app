package io.github.iodar.rest.v1.converter;

import io.github.iodar.rest.v1.dto.UserDto;
import io.github.iodar.service.core.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
class UserConverterITest {

    @Inject
    private UserConverter userConverter;

    @Test
    @DisplayName("Convert sollte vollständiges Model in Dto konvertieren")
    void convertShouldTransformModelToDto() {
        //prep
        final User user = User.builder()
                .vorname("Peter")
                .nachname("Müller")
                .geburtsdatum(LocalDate.of(2000, 1, 1))
                .build();

        // act
        final UserDto userDto = userConverter.convertToDto(user);

        // assert
        Assertions.assertAll(
                () -> assertThat(userDto.getVorname(), is(user.getVorname())),
                () -> assertThat(userDto.getNachname(), is(user.getNachname())),
                () -> assertThat(userDto.getGeburtstag(), is(getIsoDateStringOf(user.getGeburtsdatum())))
        );

    }

    @Test
    @DisplayName("Converter sollte null zurückgeben, wenn Übergabeparameter null ist")
    void convertShouldReturnNullWhenGivenNullAsParam() {
        // act
        final UserDto userDto = userConverter.convertToDto(null);

        // assert
        assertThat(userDto, is(nullValue()));
    }

    // TODO: dgr 12.05.2019 Ist dieser Test sinnvoll? Wenn es einen Validator gibt, der prüft dass alle
    //                      Daten von Außen ein Datum haben, dann kann der Zustand ohne Datum nie in der
    //                      Datenhaltun vorliegen
    @Test
    @DisplayName("Convert sollte Exception werfen, wenn Date null ist")
    void convertShouldThrowExceptionWhenDateIsNull() {
        //prep
        final User user = User.builder()
                .vorname("Peter")
                .nachname("Müller")
                .geburtsdatum(null)
                .build();

        // act and assert
        Assertions.assertThrows(NullPointerException.class, () -> userConverter.convertToDto(user));
    }

    private String getIsoDateStringOf(final LocalDate localDate) {
        return DateTimeFormatter.ISO_DATE.format(localDate);
    }
}