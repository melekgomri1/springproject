package com.example.springprojet.service;

import com.example.springprojet.model.User;
import com.example.springprojet.web.UserRegistrationDto;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
}
