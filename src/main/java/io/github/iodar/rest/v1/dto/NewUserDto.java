package io.github.iodar.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"nachname", "vorname", "geburtstag"})
public class NewUserDto {

    @JsonProperty(value = "name")
    private String nachname;
    private String vorname;
    private String geburtstag;
}
