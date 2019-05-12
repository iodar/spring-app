package io.github.iodar.rest.v1.converter;

import io.github.iodar.rest.v1.dto.NewUserDto;
import io.github.iodar.service.core.model.User;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.hamcrest.Matchers.is;

@SpringBootTest
class NewUserDtoToModelConverterITest {

    @Inject
    private NewUserDtoToModelConverter newUserDtoToModelConverter;

    @Test
    @DisplayName("NewUserDto sollte vollständig in einen User convertiert werden")
    void shouldConvertFromNewUserDtoToModel() {
        // prep
        final NewUserDto newUserDto = NewUserDto.builder()
                .nachname("Müller")
                .vorname("Peter")
                .geburtstag("2019-01-01")
                .build();

        // act
        final User user = newUserDtoToModelConverter.convert(newUserDto);

        // assert
        Assertions.assertAll(
                () -> MatcherAssert.assertThat(user.getNachname(), is(newUserDto.getNachname())),
                () -> MatcherAssert.assertThat(user.getVorname(), is(newUserDto.getVorname())),
                () -> MatcherAssert.assertThat(user.getGeburtsdatum().format(DateTimeFormatter.ISO_DATE), is(newUserDto.getGeburtstag()))
        );
    }

    // TODO: dgr 12.05.2019 sollte durch einen Validator geprüft werden
    @Test
    @DisplayName("NewUserDto sollte Exception werfen wenn Datum nicht im ISO DATE (uuuu-MM-dd) Format")
    void shouldThrowExceptionUponConvertingDtoWithWrongDateFormat() {
        // prep
        final NewUserDto newUserDto = NewUserDto.builder()
                .nachname("Müller")
                .vorname("Peter")
                .geburtstag("01.01.2019")
                .build();

        // act and assert
        Assertions.assertThrows(DateTimeParseException.class, () -> newUserDtoToModelConverter.convert(newUserDto));
    }

    // TODO: dgr 12.05.2019 sollte durch einen Validator geprüft werden
    @Test
    @DisplayName("NewUserDto sollte Exception werfen wenn Datum leer ist")
    void shouldThrowExceptionUponConvertingDtoWithWithEmptyDateString() {
        // prep
        final NewUserDto newUserDto = NewUserDto.builder()
                .nachname("Müller")
                .vorname("Peter")
                .geburtstag("")
                .build();

        // act and assert
        Assertions.assertThrows(DateTimeParseException.class, () -> newUserDtoToModelConverter.convert(newUserDto));
    }
}