package io.github.iodar.rest.v1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/greeting")
    public String getGreeting(@RequestParam(value = "json", required = false) final Boolean json) {
        if (json != null && json) {
            return "{\"greeting\":\"Greetings from Spring\"}";
        } else {
            return "Greetings from Spring";
        }
    }

}
