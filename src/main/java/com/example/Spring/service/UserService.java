package com.example.Spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Spring.entity.User;
import com.example.Spring.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(User user) {
        // Перевірка, чи такий користувач вже існує
        if (userRepository.existsById(user.getEmail())) {
            return false; // Якщо користувач вже існує, повертаємо false
        }
        userRepository.save(user);  // Зберігаємо нового користувача
        return true;
    }
}