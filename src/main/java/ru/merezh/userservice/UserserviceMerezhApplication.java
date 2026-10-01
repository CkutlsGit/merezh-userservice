package ru.merezh.userservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Userservice Merezh",
                version = "v1.0",
                description = "Эндпоинты для взаимодействия с пользователями"
        )
)
@SpringBootApplication
public class UserserviceMerezhApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceMerezhApplication.class, args);
	}

}
