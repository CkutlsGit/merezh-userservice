package ru.merezh.userservice.dto;

public record UserFormDto(
        String email,
        String login,
        String hashPassword
) {
}
