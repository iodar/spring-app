package io.github.iodar.service.core;

import io.github.iodar.rest.v1.dto.GreetingDto;

import java.time.LocalDateTime;

public interface GreetingService {

    GreetingDto getGreeting();

    GreetingDto getCustomGreeting(final String value, final LocalDateTime dateTime);
}
