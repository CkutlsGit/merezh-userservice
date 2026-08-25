package ru.merezh.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.merezh.userservice.dto.UserDto;
import ru.merezh.userservice.dto.UserFormDto;
import ru.merezh.userservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(UserFormDto createData) {
        return ResponseEntity.ok().body(userService.createUser(createData));
    }

    @PostMapping("/validate")
    public ResponseEntity<UserDto> validateUser(String email, String hashPassword) {
        return ResponseEntity.ok().body(userService.validateUser(email, hashPassword));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id) {
        return ResponseEntity.ok().body(userService.deleteUserById(id));
    }
}
