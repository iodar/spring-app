package io.github.iodar.rest.v1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${spring-app.greeting.value}")
    private String value;

    @RequestMapping("/greeting")
    public String getGreeting(@RequestParam(value = "json", required = false) final Boolean json) {
        if (json != null && json) {
            return "{\"greeting\":\"" + value + " from Spring\"}";
        } else {
            return value + " from Spring";
        }
    }

}
