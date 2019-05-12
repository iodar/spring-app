package io.github.iodar.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonPropertyOrder({"vorname", "nachname", "geburtstag"})
public class UserDto {
    private String vorname;
    @JsonProperty("name")
    private String nachname;
    private String geburtstag;
}
