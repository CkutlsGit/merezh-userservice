package ru.merezh.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для аутентификации пользователя")
public record UserLoginFormDto(

        @Schema(description = "Почта пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,

        @Schema(description = "Пароль пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
        String password
) {
}
