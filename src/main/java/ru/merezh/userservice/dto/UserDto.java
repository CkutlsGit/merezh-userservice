package ru.merezh.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.merezh.userservice.entity.enums.Role;

@Schema(description = "Возвращаемые данные")
public record UserDto(

        @Schema(description = "Индефикатор пользователя")
        Long id,

        @Schema(description = "Роль пользователя")
        Role role
) {
}
