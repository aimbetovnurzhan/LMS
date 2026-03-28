package com.lms;

import com.lms.entity.User;
import com.lms.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository, PasswordEncoder encoder) {
        return (args) -> {
            String rawPassword = "adminPassword";
            String encodedPassword = encoder.encode(rawPassword);
            // Создаем и сохраняем тестового пользователя
            User testUser = User.builder()
                    .username("admin")
                    .passwordHash(encodedPassword)
                    .build();

            repository.save(testUser);
            System.out.println(">>> Password encoded: " + encodedPassword);
        };
    }
}