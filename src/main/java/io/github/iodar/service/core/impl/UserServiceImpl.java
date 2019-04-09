package io.github.iodar.service.core.impl;

import io.github.iodar.rest.v1.dto.AdresseDto;
import io.github.iodar.rest.v1.dto.UserDto;
import io.github.iodar.service.core.UserService;

import javax.inject.Named;

import static java.time.LocalDate.of;

@Named
public class UserServiceImpl implements UserService {
    @Override
    public UserDto getNewUser() {
        final AdresseDto adresseDto = new AdresseDto()
                .setStrasse("Heimfriedstraße")
                .setHausnummer("7A")
                .setOrt("Berlin")
                .setPostleitzahl("13125");
        return new UserDto()
                .setNachname("Granzow")
                .setVorname("Dario")
                .setGeburtstag(of(1996, 5, 31))
                .setAdresseDto(adresseDto);
    }
}
