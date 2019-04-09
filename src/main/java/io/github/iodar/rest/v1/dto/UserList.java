package io.github.iodar.rest.v1.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserList {
    private List<UserDto> users;
}
