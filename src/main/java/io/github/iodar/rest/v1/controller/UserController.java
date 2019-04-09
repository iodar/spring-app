package io.github.iodar.rest.v1.controller;

import io.github.iodar.rest.v1.dto.UserDto;
import io.github.iodar.service.core.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Inject
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public UserDto getUser() {
        return userService.getNewUser();
    }
}
