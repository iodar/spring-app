package io.github.iodar.service.core.impl;

import io.github.iodar.persistence.converter.UserDboConverter;
import io.github.iodar.persistence.entities.UserDbo;
import io.github.iodar.persistence.repos.UserRepo;
import io.github.iodar.service.core.UserService;
import io.github.iodar.service.core.model.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Named
public class UserServiceImpl implements UserService {

    private final UserDboConverter userDboConverter;
    private final UserRepo userRepo;

    @Inject
    public UserServiceImpl(final UserDboConverter userDboConverter, final UserRepo userRepo) {
        this.userDboConverter = userDboConverter;
        this.userRepo = userRepo;
    }

    @Override
    public List<User> findAll() {
        return this.userRepo.findAll().stream()
                .map(this.userDboConverter::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findByNachname(final String nachname) {
        if (nachname == null) {
            return emptyList();
        } else {
            return this.userRepo.findByNachname(nachname).stream()
                    .map(this.userDboConverter::convertToModel)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<User> findByVorname(final String vorname) {
        if (vorname == null) {
            return emptyList();
        } else {
            return this.userRepo.findByVorname(vorname).stream()
                    .map(this.userDboConverter::convertToModel)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<User> findByNachnameAndVorname(final String nachname, final String vorname) {
        if (vorname == null) {
            return emptyList();
        } else {
            return this.userRepo.findByNachnameAndVorname(nachname, vorname).stream()
                    .map(this.userDboConverter::convertToModel)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Optional<User> findByUserId(final Long userId) {
        return userId == null
                ? Optional.empty()
                : this.userRepo.findById(userId).map(this.userDboConverter::convertToModel);
    }

    @Override
    public Optional<User> createNewUser(final User user) {
        if (user == null) {
            return Optional.empty();
        } else {
            final UserDbo persistedUser = this.userRepo.saveAndFlush(this.userDboConverter.convertToDbo(user));
            return Optional.ofNullable(this.userDboConverter.convertToModel(persistedUser));
        }
    }

}
