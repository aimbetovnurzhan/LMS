package com.lms.service;

import com.lms.dto.UserRegistrationDto;
import com.lms.entity.User;
import com.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Говорим Spring: "Это сервисный слой, создай этот объект при старте"
@RequiredArgsConstructor // Автоматически создает конструктор для внедрения зависимостей
public class UserServiceImpl implements UserService {

    // Эти инструменты Spring "подложит" нам сам через конструктор
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserRegistrationDto registrationDto) {
        // 1. ПРОВЕРКА: Есть ли уже такой пользователь?
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new RuntimeException("Error: User with the same name already exists!");
        }

        // 2. ШИФРОВАНИЕ: Берем "сырой" пароль и превращаем в хеш
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());

        // 3. ПРЕОБРАЗОВАНИЕ: Из "конверта" (DTO) в "сущность" (Entity)
        User newUser = User.builder()
                .username(registrationDto.getUsername())
                .passwordHash(encodedPassword)
                .build();

        // 4. СОХРАНЕНИЕ: Отправляем готовую сущность в базу
        return userRepository.save(newUser);
    }
}