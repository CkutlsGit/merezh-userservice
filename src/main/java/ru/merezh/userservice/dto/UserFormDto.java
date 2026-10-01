package ru.merezh.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для создания пользователя")
public record UserFormDto(

        @Schema(description = "Почта пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
        String email,

        @Schema(description = "Логин пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
        String login,

        @Schema(description = "Хэш пароль пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
        String hashPassword
) {
}
