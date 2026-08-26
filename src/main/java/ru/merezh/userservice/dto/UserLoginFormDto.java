package ru.merezh.userservice.dto;

public record UserLoginFormDto(
        String email,
        String password
) {
}
