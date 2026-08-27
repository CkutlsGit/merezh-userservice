package ru.merezh.userservice.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.merezh.userservice.exception.UserException;
import ru.merezh.userservice.exception.dto.ExceptionDto;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionDto> userExceptionHandler(UserException e) {
        return ResponseEntity.status(e.getCode()).body(new ExceptionDto(
                e.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> exceptionHandler(Exception e) {
        log.error("Ошибка в классе - {}", e.getClass());
        log.error("Ошибка - {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionDto(
                "Ошибка сервиса"
        ));
    }
}
