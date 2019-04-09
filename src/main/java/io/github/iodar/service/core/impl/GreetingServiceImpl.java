package io.github.iodar.service.core.impl;

import io.github.iodar.rest.v1.dto.GreetingDto;
import io.github.iodar.service.core.GreetingService;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Named
public class GreetingServiceImpl implements GreetingService {

    @Value("${spring-app.advanced-greeting.value}")
    private String value;

    @Override
    public GreetingDto getGreeting() {
        return new GreetingDto()
                .setGreeting(value)
                .setDateTime(now().toString());
    }

    @Override
    public GreetingDto getCustomGreeting(final String value, final LocalDateTime dateTime) {
        return new GreetingDto()
                .setGreeting(value)
                .setDateTime(dateTime.toString());
    }
}
