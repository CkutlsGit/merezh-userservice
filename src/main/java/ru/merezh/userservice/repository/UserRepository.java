package ru.merezh.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.merezh.userservice.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmailAndHashPassword(String email, String hashPassword);
    boolean existsUserByEmail(String email);
    boolean existsUserByLogin(String login);
}
