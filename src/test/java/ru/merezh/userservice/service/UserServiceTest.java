package ru.merezh.userservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.merezh.userservice.dto.UserDto;
import ru.merezh.userservice.dto.UserFormDto;
import ru.merezh.userservice.entity.User;
import ru.merezh.userservice.entity.enums.Role;
import ru.merezh.userservice.exception.UserException;
import ru.merezh.userservice.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_ExistsEmail_ThrowsUserException() {
        UserFormDto mockUserFormDto = new UserFormDto(
                "Test@gmail.test",
                "test",
                "hash_password"
        );

        when(userRepository.existsUserByEmail(mockUserFormDto.email())).thenReturn(true);

        UserException result = assertThrows(UserException.class, () -> {
            userService.createUser(mockUserFormDto);
        });

        String expected = "Почта уже занята";

        assertEquals(expected, result.getMessage());
    }

    @Test
    void createUser_ExistsLogin_ThrowsUserException() {
        UserFormDto mockUserFormDto = new UserFormDto(
                "Test@gmail.test",
                "test",
                "hash_password"
        );

        when(userRepository.existsUserByEmail(mockUserFormDto.email())).thenReturn(false);
        when(userRepository.existsUserByLogin(mockUserFormDto.login())).thenReturn(true);

        UserException result = assertThrows(UserException.class, () -> {
            userService.createUser(mockUserFormDto);
        });

        String expected = "Логин уже занят";

        assertEquals(expected, result.getMessage());
    }

    @Test
    void createUser_ValidData_SaveUser() {
        UserFormDto mockUserFormDto = new UserFormDto(
                "Test@gmail.test",
                "test",
                "hash_password"
        );

        when(userRepository.existsUserByEmail(mockUserFormDto.email())).thenReturn(false);
        when(userRepository.existsUserByLogin(mockUserFormDto.login())).thenReturn(false);

        User savedUser = new User(
                mockUserFormDto.email(),
                mockUserFormDto.login(),
                mockUserFormDto.hashPassword()
        );
        savedUser.setId(1L);
        savedUser.setRole(Role.USER);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserDto result = userService.createUser(mockUserFormDto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(Role.USER, result.role());

        verify(userRepository).save(argThat(user ->
                        user.getEmail().equals("Test@gmail.test") &&
                        user.getLogin().equals("test") &&
                        user.getHashPassword().equals("hash_password")
        ));
    }
}