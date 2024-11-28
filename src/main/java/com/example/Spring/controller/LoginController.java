package com.example.Spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    // GET mapping for displaying the login page
    @GetMapping("/login")
		public String showLoginForm() {
        return "login";  // Return the login page
    }

		@PostMapping("/login")
		public String login(@RequestParam String email, @RequestParam String password, Model model) {
				System.out.println("Email: " + email);  // Логування значень email
				System.out.println("Password: " + password);  // Логування значень паролю
		
				// Створення аутентифікаційного токена
				UsernamePasswordAuthenticationToken authenticationToken = 
								new UsernamePasswordAuthenticationToken(email, password);
		
				try {
						// Аутентифікація користувача
						Authentication authentication = authenticationManager.authenticate(authenticationToken);
		
						// Успішна аутентифікація
						SecurityContextHolder.getContext().setAuthentication(authentication);
						return "redirect:/home";  // Переходьте на головну сторінку
				} catch (Exception e) {
						model.addAttribute("error", "Невірний логін або пароль");
						return "login";  // Повернення на сторінку логіну при помилці
				}
		}
}