package ru.merezh.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.merezh.userservice.dto.UserDto;
import ru.merezh.userservice.dto.UserFormDto;
import ru.merezh.userservice.dto.UserLoginFormDto;
import ru.merezh.userservice.entity.User;
import ru.merezh.userservice.exception.UserException;
import ru.merezh.userservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto createUser(UserFormDto userFormDto) {
        if (userRepository.existsUserByEmail(userFormDto.email())) {
            throw new UserException("Почта уже занята");
        }

        if (userRepository.existsUserByLogin(userFormDto.login())) {
            throw new UserException("Логин уже занят");
        }

        User user = userRepository.save(new User(
                userFormDto.email(),
                userFormDto.login(),
                userFormDto.hashPassword()
        ));

        return new UserDto(
                user.getId(),
                user.getRole()
        );
    }

    @Transactional(readOnly = true)
    public UserDto validateUser(UserLoginFormDto userLoginFormDto) {
        User user = userRepository.findUserByEmail(userLoginFormDto.email())
                .orElseThrow(() -> new UserException("Пользователь не существует", HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(userLoginFormDto.password(), user.getHashPassword())) {
            throw new UserException("Неверные данные", HttpStatus.BAD_REQUEST);
        }

        return new UserDto(
                user.getId(),
                user.getRole()
        );
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("Пользователя не существует", HttpStatus.NOT_FOUND));

        return new UserDto(
                user.getId(),
                user.getRole()
        );
    }

    @Transactional
    public String deleteUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("Пользователя не существует", HttpStatus.NOT_FOUND));

        userRepository.delete(user);

        return "Пользователь с " + id + " удален";
    }
}
