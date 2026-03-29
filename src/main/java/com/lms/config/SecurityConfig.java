package com.lms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // Включаем возможность настройки безопасности
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Отключаем CSRF (для тестов через терминал это обязательно)
                .csrf(csrf -> csrf.disable())

                // 2. Настраиваем "белый список" адресов
                .authorizeHttpRequests(auth -> auth
                        // Разрешаем консоль базы данных
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        // РАЗРЕШАЕМ регистрацию и логин всем (наш контроллер)
                        .requestMatchers(new AntPathRequestMatcher("/api/auth/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/hello/**")).permitAll()
                        // Все остальные запросы потребуют авторизацию
                        .anyRequest().authenticated()
                )

                // 3. Настройка для консоли H2 (чтобы она открывалась в браузере)
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}