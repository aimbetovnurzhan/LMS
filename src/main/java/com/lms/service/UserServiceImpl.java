package com.lms.service;

import com.lms.dto.UserRegistrationDto;
import com.lms.entity.User;
import com.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserRegistrationDto registrationDto) {
        // 1. ПРОВЕРКА: Есть ли уже такой пользователь?
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            throw new RuntimeException("Error: User with the same name already exists!");
        }

        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());

        User newUser = User.builder()
                .username(registrationDto.getUsername())
                .passwordHash(encodedPassword)
                .build();

        return userRepository.save(newUser);
    }

    @Override
    public User findByUsername(String username) {
        // Ищем в базе через репозиторий.
        // Если не нашли — возвращаем null, чтобы контроллер понял: юзера нет.
        return userRepository.findByUsername(username).orElse(null);
    }
}