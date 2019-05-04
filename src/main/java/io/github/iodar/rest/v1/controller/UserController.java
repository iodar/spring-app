package io.github.iodar.rest.v1.controller;

import io.github.iodar.rest.v1.converter.UserConverter;
import io.github.iodar.rest.v1.dto.UserListDto;
import io.github.iodar.service.core.UserService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserConverter userConverter;

    @Inject
    public UserController(final UserService userService, final UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping
    public UserListDto getUserByNachnameOrVorname(@RequestParam(value = "nachname", required = false) final String nachname,
                                                  @RequestParam(value = "vorname", required = false) final String vorname) {
        if (nachname != null && vorname != null) {
            return getUsersByNachnameAndVorname(nachname, vorname);
        } else if (nachname != null) {
            return getUsersByNachname(nachname);
        } else if (vorname != null) {
            return getUsersByVorname(vorname);
        }

        return null;
    }

    private UserListDto getUsersByNachnameAndVorname(@RequestParam(value = "nachname", required = false) String nachname, @RequestParam(value = "vorname", required = false) String vorname) {
        return new UserListDto().setUsers(this.userService.findByNachnameAndVorname(nachname, vorname).stream()
                .map(user -> this.userConverter.convertToDto(user))
                .collect(Collectors.toList()));
    }

    private UserListDto getUsersByNachname(@RequestParam(value = "nachname", required = false) String nachname) {
        return new UserListDto().setUsers(this.userService.findByNachname(nachname).stream()
                .map(user -> this.userConverter.convertToDto(user))
                .collect(Collectors.toList()));
    }

    private UserListDto getUsersByVorname(@RequestParam(value = "vorname", required = false) String vorname) {
        return new UserListDto().setUsers(this.userService.findByVorname(vorname).stream()
                .map(user -> this.userConverter.convertToDto(user))
                .collect(Collectors.toList()));
    }
}
