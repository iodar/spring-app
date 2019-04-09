package io.github.iodar.rest.v1.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdresseDto {
    private String strasse;
    private String hausnummer;
    private String postleitzahl;
    private String ort;
}
