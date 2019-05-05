package io.github.iodar.service.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    private Long userId;
    private String nachname;
    private String vorname;
    private LocalDate geburtsdatum;
}
