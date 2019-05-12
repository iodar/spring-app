package io.github.iodar.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonPropertyOrder({"id", "vorname", "nachname", "geburtstag"})
public class UserDto {
    private Long id;
    private String vorname;
    @JsonProperty("name")
    private String nachname;
    private String geburtstag;
}
