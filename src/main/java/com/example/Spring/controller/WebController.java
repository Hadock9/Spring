package com.example.Spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Spring.entity.Game;
import com.example.Spring.service.GameService;

@Controller
public class WebController {

    private final GameService gameService;

    @Autowired
    public WebController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("title", "Головна");
        return "home";  
    }

    @GetMapping("/games")
    public String getGames(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        model.addAttribute("title", "Список Ігор");
        return "games"; 
    }
}