package com.lms;

import com.lms.dto.UserRegistrationDto;
import com.lms.entity.User;
import com.lms.repository.UserRepository;
import com.lms.service.UserService;
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
    public CommandLineRunner demo(UserService userService) {
        return (args) -> {
            UserRegistrationDto dto = new UserRegistrationDto();
            dto.setUsername("student_java");
            dto.setPassword("secret123");

            userService.registerUser(dto);
            //userService.registerUser(dto);
            System.out.println(">>> User registered succesfully through the service!");
        };
    }
}