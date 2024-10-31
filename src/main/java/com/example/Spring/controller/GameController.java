package com.example.Spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Spring.entity.Game;
import com.example.Spring.service.GameService;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public String getGames(Model model) {
        List<Game> games = gameService.getAllGames(); 
        model.addAttribute("games", games); 
        return "games"; 
    }
}