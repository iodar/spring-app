package io.github.iodar.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"nachname", "vorname", "geburtstag"})
public class NewUserDto {

    @NotEmpty
    @JsonProperty(value = "name")
    private String nachname;
    @NotEmpty
    private String vorname;
    @NotEmpty
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String geburtstag;
}
