package io.github.iodar.rest.v1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class GreetingDto {
    private String greeting;
    private String dateTime;
}
