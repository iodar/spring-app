package io.github.iodar.rest.v1.controller;

import io.github.iodar.rest.v1.converter.NewUserDtoToModelConverter;
import io.github.iodar.rest.v1.converter.UserConverter;
import io.github.iodar.rest.v1.dto.ActionDto;
import io.github.iodar.rest.v1.dto.NewUserDto;
import io.github.iodar.rest.v1.dto.UserDto;
import io.github.iodar.rest.v1.dto.UserListDto;
import io.github.iodar.service.core.UserService;
import io.github.iodar.service.core.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserService userService;
    private UserConverter userConverter;
    private NewUserDtoToModelConverter newUserDtoToModelConverter;

    @Inject
    public UserController(final UserService userService, final UserConverter userConverter, final NewUserDtoToModelConverter newUserDtoToModelConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
        this.newUserDtoToModelConverter = newUserDtoToModelConverter;
    }

    @GetMapping("/users")
    public UserListDto getUserByNachnameOrVorname(@RequestParam(value = "nachname", required = false) final String nachname,
                                                  @RequestParam(value = "vorname", required = false) final String vorname) {
        if (nachname != null && vorname != null) {
            return getUsersByNachnameAndVorname(nachname, vorname);
        } else if (nachname != null) {
            return getUsersByNachname(nachname);
        } else if (vorname != null) {
            return getUsersByVorname(vorname);
        } else {
            return getUsers();
        }

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "userId") final Long userId) {
        final Optional<User> user = this.userService.findByUserId(userId);
        return user.map(this.userConverter::convertToDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/users")
    public ResponseEntity<ActionDto> createNewUser(@Valid @RequestBody final NewUserDto newUser) {
        return this.userService.createNewUser(this.newUserDtoToModelConverter.convert(newUser))
                .map(user -> {
                    final ActionDto actionDto = ActionDto.builder()
                            .message("New user successfully created")
                            .build();
                    return ResponseEntity.ok(actionDto);
                })
                .orElseGet(() -> {
                    final ActionDto actionDto = ActionDto.builder()
                            .message("Creation of new user failed")
                            .build();
                    return ResponseEntity.badRequest().body(actionDto);
                });
    }

    private UserListDto getUsersByNachnameAndVorname(final String nachname, final String vorname) {
        return new UserListDto().setUsers(this.userService.findByNachnameAndVorname(nachname, vorname).stream()
                .map(user -> this.userConverter.convertToDto(user))
                .collect(Collectors.toList()));
    }

    private UserListDto getUsersByNachname(final String nachname) {
        return new UserListDto().setUsers(this.userService.findByNachname(nachname).stream()
                .map(user -> this.userConverter.convertToDto(user))
                .collect(Collectors.toList()));
    }

    private UserListDto getUsersByVorname(final String vorname) {
        return new UserListDto().setUsers(this.userService.findByVorname(vorname).stream()
                .map(user -> this.userConverter.convertToDto(user))
                .collect(Collectors.toList()));
    }

    private UserListDto getUsers() {
        return new UserListDto().setUsers(this.userService.findAll().stream()
                .map(this.userConverter::convertToDto)
                .collect(Collectors.toList()));
    }
}
