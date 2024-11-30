package com.example.Spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring.entity.Bet;
import com.example.Spring.service.BetService;

@RestController
@RequestMapping("/api/bets")
public class BetController {

    @Autowired
    private BetService betService;

    // Отримати всі ставки
    @GetMapping
    public List<Bet> getAllBets() {
        return betService.getAllBets();
    }

    // Отримати ставку за ID
    @GetMapping("/{id}")
    public ResponseEntity<Bet> getBetById(@PathVariable Integer id) {
        return betService.findByBetId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Створити нову ставку
    @PostMapping
    public ResponseEntity<Bet> createBet(@RequestBody Bet bet) {
        Bet createdBet = betService.createBet(bet);
        return ResponseEntity.ok(createdBet);
    }

    // Оновити ставку
    @PutMapping("/{id}")
    public ResponseEntity<Bet> updateBet(@PathVariable Integer id, @RequestBody Bet betDetails) {
        Optional<Bet> existingBet = betService.findByBetId(id);

        if (existingBet.isPresent()) {
            Bet updatedBet = betService.updateBet(id, betDetails);
            return ResponseEntity.ok(updatedBet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Видалити ставку
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBet(@PathVariable Integer id) {
        Optional<Bet> existingBet = betService.findByBetId(id);

        if (existingBet.isPresent()) {
            betService.deleteBet(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}