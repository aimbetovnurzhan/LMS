# Learning Management System (LMS)
Учебный проект на Spring Boot 3.4.4 и Java 21.

## Стек технологий
- **Java 21 (LTS)**
- **Spring Boot 3.4.4**
- **Spring Data JPA**
- **H2 In-Memory Database**
- **Lombok**

## Как запустить
1. Склонируйте репозиторий.
2. Выполните команду `./gradlew bootRun`.
3. Консоль базы данных доступна по адресу: `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:mem:lmsdb`
    - User: `sa`