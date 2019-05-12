package io.github.iodar.rest.v1.converter;

import io.github.iodar.rest.v1.dto.UserDto;
import io.github.iodar.service.core.model.User;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Named
public class UserConverter {

    public UserDto convertToDto(final User user) {
        if (user == null) {
            return null;
        } else {
            return new UserDto()
                    .setNachname(user.getNachname())
                    .setVorname(user.getVorname())
                    .setGeburtstag(fromDate(user.getGeburtsdatum()))
                    .setId(user.getId());
        }
    }

    private String fromDate(final LocalDate date) {
        return DateTimeFormatter.ISO_DATE.format(date);
    }
}
