package ru.merezh.userservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserException extends RuntimeException {

    private HttpStatus code;

    public UserException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }

    public UserException(String message) {
        super(message);
        this.code = HttpStatus.CONFLICT;
    }
}
