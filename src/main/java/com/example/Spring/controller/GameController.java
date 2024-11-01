package com.example.Spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring.entity.Game;
import com.example.Spring.service.GameService;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

 
    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
			public Optional<Game> getGameById(@PathVariable Integer id) {
    return gameService.findByGameId(id);
}
}