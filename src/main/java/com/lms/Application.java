package com.lms;

import com.lms.entity.User;
import com.lms.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // Создаем и сохраняем тестового пользователя
            User testUser = User.builder()
                    .username("admin")
                    .passwordHash("hashed_password_123")
                    .build();

            repository.save(testUser);
            System.out.println(">>> Тестовый пользователь сохранен в БД!");
        };
    }
}