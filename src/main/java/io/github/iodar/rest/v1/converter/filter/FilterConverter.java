package io.github.iodar.rest.v1.converter.filter;

import io.github.iodar.rest.v1.dto.filter.UserFilterDto;
import io.github.iodar.service.core.model.filter.UserFilter;

import javax.inject.Named;

@Named
public class FilterConverter {

    public UserFilter convertToModel(final UserFilterDto userFilterDto) {
        return userFilterDto == null
            ? null
            : UserFilter.builder()
                .vorname(userFilterDto.getVorname())
                .nachname(userFilterDto.getNachname())
                .build();
    }
}
