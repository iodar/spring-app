package io.github.iodar.rest.v1.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFilterDto {

    public static final int MAX_VORNAME_LENGTH = 255;
    public static final int MAX_NACHNAME_LENGTH = 255;

    @Size(max = MAX_VORNAME_LENGTH)
    private String vorname;
    @Size(max = MAX_NACHNAME_LENGTH)
    private String nachname;

    public UserFilterDto setVorname(String vorname) throws UnsupportedEncodingException {
        this.vorname = urlDecodeIfValuePresent(vorname);
        return this;
    }

    public UserFilterDto setNachname(String nachname) throws UnsupportedEncodingException {
        this.nachname = urlDecodeIfValuePresent(nachname);
        return this;
    }

    private String urlDecodeIfValuePresent(String value) throws UnsupportedEncodingException {
        return value != null
                ? URLDecoder.decode(value, StandardCharsets.UTF_8.displayName())
                : null;
    }

}
