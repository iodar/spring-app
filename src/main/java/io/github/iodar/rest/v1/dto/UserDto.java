package io.github.iodar.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class UserDto {
    private String vorname;
    @JsonProperty("name")
    private String nachname;
    private LocalDate geburtstag;
    @JsonProperty("adresse")
    private AdresseDto adresseDto;
}
