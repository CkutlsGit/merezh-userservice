package ru.merezh.userservice.dto;

import ru.merezh.userservice.entity.enums.Role;

public record UserDto(
        Long id,
        Role role
) {
}
