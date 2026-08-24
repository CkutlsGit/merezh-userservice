package ru.merezh.userservice.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.merezh.userservice.entity.enums.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    public User(String email, String login, String hashPassword) {
        this.email = email;
        this.login = login;
        this.hashPassword = hashPassword;
        this.role = Role.USER;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String hashPassword;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Role role;
}
