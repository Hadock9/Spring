package com.example.Spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}