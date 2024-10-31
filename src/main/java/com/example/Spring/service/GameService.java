package com.example.Spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Spring.entity.Game;
import com.example.Spring.repository.GameRepository;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

   
    public Optional<Game> getGameById(Integer id) {
        return gameRepository.findById(id);
    }

    
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

   
    public void deleteGame(Integer id) {
        gameRepository.deleteById(id);
    }
}