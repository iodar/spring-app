package io.github.iodar.rest.v1.dto.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFilterDto {

    private String vorname;
    private String nachname;

    public UserFilterDto setVorname(String vorname) {
        this.vorname = urlDecodeIfValuePresent(vorname);
        return this;
    }

    public UserFilterDto setNachname(String nachname) {
        this.nachname = urlDecodeIfValuePresent(nachname);
        return this;
    }

    private String urlDecodeIfValuePresent(String value) {
        return value != null
                ? URLDecoder.decode(value, StandardCharsets.UTF_8)
                : null;
    }

}
