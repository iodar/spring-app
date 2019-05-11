package io.github.iodar.service.core;

import io.github.iodar.rest.v1.dto.UserDto;
import io.github.iodar.service.core.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto getNewUser();

    List<User> findAll();

    List<User> findByNachname(final String nachname);

    List<User> findByVorname(final String vorname);

    List<User> findByNachnameAndVorname(final String nachname, final String vorname);

    Optional<User> findByUserId(final Long userId);

    Optional<User> createNewUser(final User user);
}
