package com.example.springprojet.service;

import com.example.springprojet.model.Role;
import com.example.springprojet.model.User;
import com.example.springprojet.repository.UserRepository;
import com.example.springprojet.web.UserRegistrationDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstname(),
                registrationDto.getLastname(),
                registrationDto.getEmail(),
                registrationDto.getPassword(),
                Arrays.asList(new Role("ROLE_USER"))
        );
        return userRepository.save(user);
    }
}
