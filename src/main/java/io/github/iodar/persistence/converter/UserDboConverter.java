package io.github.iodar.persistence.converter;

import io.github.iodar.persistence.entities.UserDbo;
import io.github.iodar.service.core.model.User;

import javax.inject.Named;

@Named
public class UserDboConverter {

    public UserDbo convertToDbo(final User user) {
        if (user == null) {
            return null;
        } else {
            return new UserDbo()
                    .setVorname(user.getVorname())
                    .setNachname(user.getNachname())
                    .setGeburtsdatum(user.getGeburtsdatum());
        }
    }

    public User convertToModel(final UserDbo userDbo) {
        if (userDbo == null) {
            return null;
        } else {
            return new User()
                    .setVorname(userDbo.getVorname())
                    .setNachname(userDbo.getNachname())
                    .setGeburtsdatum(userDbo.getGeburtsdatum())
                    .setId(userDbo.getId());
        }
    }
}
