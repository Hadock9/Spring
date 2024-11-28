package com.example.Spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Spring.entity.User;
import com.example.Spring.model.RegistrationForm;
import com.example.Spring.repository.UserRepository;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;  // Використовуємо репозиторій для доступу до бази даних

    @Autowired
    private PasswordEncoder passwordEncoder;  // Використовуємо PasswordEncoder для хешування паролю

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());  // Показуємо форму реєстрації
        return "register";  // Повертаємо шаблон реєстрації
    }

    @PostMapping("/register")
    public String registerUser(RegistrationForm form) {
        // Перевірка чи існує вже користувач з таким email
        if (userRepository.existsById(form.getEmail())) {
            return "redirect:/register?error=email";  // Якщо такий email вже існує, повертаємо помилку
        }

        // Створюємо нового користувача
        User user = new User();
        user.setEmail(form.getEmail());
        user.setName(form.getName());

        // Хешуємо пароль перед збереженням
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encodedPassword);

        // Зберігаємо користувача в базі даних
        userRepository.save(user);

        return "redirect:/login";  // Після успішної реєстрації перенаправляємо на сторінку логіну
    }
}