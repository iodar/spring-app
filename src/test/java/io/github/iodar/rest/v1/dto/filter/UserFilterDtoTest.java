package io.github.iodar.rest.v1.dto.filter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class UserFilterDtoTest {

    @Test
    @DisplayName("Vorname sollte bei Setzen der Property URL dekodiert werden")
    void setVornameShouldUrlDecodeStringUponSettingProperty() throws UnsupportedEncodingException {
        //prep
        String unencodedVorname = "Peter Meier";
        final String urlEncodedVorname = getUrlEncodedStringOf(unencodedVorname);
        final UserFilterDto userFilterDto = new UserFilterDto();

        // act
        userFilterDto.setVorname(urlEncodedVorname);

        // assert
        assertThat(userFilterDto.getVorname(), is(unencodedVorname));
    }

    @Test
    @DisplayName("Nachname sollte bei Setzen der Property URL dekodiert werden")
    void setNachnameShouldUrlDecodeStringUponSettingProperty() throws UnsupportedEncodingException {
        //prep
        String unencodedNachname = "Meier Hagedorn";
        final String urlEncodedNachname = getUrlEncodedStringOf(unencodedNachname);
        final UserFilterDto userFilterDto = new UserFilterDto();

        // act
        userFilterDto.setNachname(urlEncodedNachname);

        // assert
        assertThat(userFilterDto.getNachname(), is(unencodedNachname));
    }

    @Test
    @DisplayName("Setzen der Property mit Parameter null sollte keine Exception erzeugen und null setzen")
    void setNachnameShouldNotThrowExceptionAndReturnNullUponCallingSetterWithParameterNull() throws UnsupportedEncodingException {
        //prep
        final UserFilterDto userFilterDto = new UserFilterDto();

        // act
        userFilterDto.setNachname(null);

        // assert
        assertThat(userFilterDto.getNachname(), is(nullValue()));
    }

    private String getUrlEncodedStringOf(final String unencodedString) {
        return URLEncoder.encode(unencodedString, StandardCharsets.UTF_8);
    }
}