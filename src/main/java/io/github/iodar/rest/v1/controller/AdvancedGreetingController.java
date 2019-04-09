package io.github.iodar.rest.v1.controller;

import io.github.iodar.rest.v1.dto.GreetingDto;
import io.github.iodar.service.core.GreetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_XML_VALUE;

@RestController
@RequestMapping(path = "/advanced", produces = {APPLICATION_JSON_VALUE, TEXT_XML_VALUE})
public class AdvancedGreetingController {

    private GreetingService greetingService;

    @Inject
    public AdvancedGreetingController(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public GreetingDto getGreeting() {
        return greetingService.getGreeting();
    }
}
