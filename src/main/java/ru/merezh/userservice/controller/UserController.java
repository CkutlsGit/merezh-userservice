package ru.merezh.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.merezh.userservice.dto.UserDto;
import ru.merezh.userservice.dto.UserFormDto;
import ru.merezh.userservice.dto.UserLoginFormDto;
import ru.merezh.userservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Получить весь список пользователей")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить пользователя по id")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping("/create")
    @Operation(summary = "Создания пользователя")
    public ResponseEntity<UserDto> createUser(@RequestBody UserFormDto createData) {
        return ResponseEntity.ok().body(userService.createUser(createData));
    }

    @PostMapping("/validate")
    @Operation(summary = "Проверка верно ли введенны данные и существует такой пользователь")
    public ResponseEntity<UserDto> validateUser(@RequestBody UserLoginFormDto validateData) {
        return ResponseEntity.ok().body(userService.validateUser(validateData));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя по id")
    public ResponseEntity<String> deleteUserById(@PathVariable long id) {
        return ResponseEntity.ok().body(userService.deleteUserById(id));
    }
}
