package io.github.iodar.rest.v1.converter.filter;

import io.github.iodar.rest.v1.dto.filter.UserFilterDto;
import io.github.iodar.service.core.model.filter.UserFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
class FilterConverterTest {

    @Inject
    private FilterConverter filterConverter;

    @Test
    @DisplayName("Converter sollte Dto => Model konvertieren und alle Properties setzen")
    void convertShouldConvertDtoToModelWithAllProperties() {
        //prep
        final UserFilterDto userFilterDto = UserFilterDto.builder()
                .nachname("Müller")
                .vorname("Peter")
                .build();

        // act
        final UserFilter userFilter = filterConverter.convertToModel(userFilterDto);

        // assert
        Assertions.assertAll(
                () -> assertThat(userFilter.getNachname(), is(userFilterDto.getNachname())),
                () -> assertThat(userFilter.getVorname(), is(userFilterDto.getVorname()))
        );
    }

    @Test
    @DisplayName("Converter sollte null zurückgeben, wenn null als Parameter übergeben wurde")
    void convertShouldReturnNullUponGettingCalledWithParamNull() {
        //prep
        final UserFilter userFilter = filterConverter.convertToModel(null);

        // assert
        assertThat(userFilter, is(nullValue()));
    }
}