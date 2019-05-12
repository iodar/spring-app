package io.github.iodar.rest.v1.converter;

import io.github.iodar.rest.v1.dto.NewUserDto;
import io.github.iodar.service.core.model.User;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Named
public class NewUserDtoToModelConverter implements ToModelConverter<User, NewUserDto> {

    @Override
    public User convert(final NewUserDto newUserDto) {
        return User.builder()
                .geburtsdatum(toLocalDate(newUserDto.getGeburtstag()))
                .nachname(newUserDto.getNachname())
                .vorname(newUserDto.getVorname())
                .build();
    }

    private LocalDate toLocalDate(final String dateStringRepresentation) {
        return LocalDate.parse(dateStringRepresentation, DateTimeFormatter.ISO_DATE);
    }
}
