package com.lms.service;

import com.lms.dto.UserRegistrationDto;
import com.lms.entity.User;

public interface UserService {
    User registerUser(UserRegistrationDto registrationDto);
}
