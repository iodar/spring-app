package io.github.iodar.rest.v1.converter;

import io.github.iodar.rest.v1.dto.UserDto;
import io.github.iodar.service.core.model.User;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Named
public class UserConverter {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    public User convertToModel(final UserDto userDto) {
        if (userDto == null) {
            return null;
        } else {
            return new User()
                    .setVorname(userDto.getVorname())
                    .setNachname(userDto.getNachname())
                    .setGeburtsdatum(toDate(userDto.getGeburtstag()));
        }
    }

    public UserDto convertToDto(final User user) {
        if (user == null) {
            return null;
        } else {
            return new UserDto()
                    .setNachname(user.getNachname())
                    .setVorname(user.getVorname())
                    .setGeburtstag(fromDate(user.getGeburtsdatum()));
        }
    }

    private LocalDate toDate(final String dateString) {
        return LocalDate.parse(dateString, dateTimeFormatter);
    }

    private String fromDate(final LocalDate date) {
        return dateTimeFormatter.format(date);
    }
}
