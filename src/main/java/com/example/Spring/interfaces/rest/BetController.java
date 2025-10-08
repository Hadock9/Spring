package com.example.Spring.interfaces.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring.application.bet.BetCommandService;
import com.example.Spring.application.bet.BetQueryService;
import com.example.Spring.application.bet.commands.PlaceBetCommand;
import com.example.Spring.domain.bet.Bet;
import com.example.Spring.domain.bet.Bet.BetId;
import com.example.Spring.domain.bet.Bet.BetLost;
import com.example.Spring.domain.bet.Bet.BetPlaced;
import com.example.Spring.domain.bet.Bet.BetWon;
import com.example.Spring.domain.bet.Bet.Coefficient;
import com.example.Spring.domain.bet.Bet.MatchId;
import com.example.Spring.domain.bet.Bet.Money;
import com.example.Spring.domain.bet.Bet.TeamId;
import com.example.Spring.domain.bet.Bet.UserId;

@RestController
@RequestMapping("/api/bets")
public class BetController {
    private final BetCommandService commandService;
    private final BetQueryService queryService;

    public BetController(BetCommandService commandService, BetQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping
    public List<Bet> getAllBets() {
        return queryService.getAllBets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bet> getBetById(@PathVariable Integer id) {
        return queryService.getBetById(new BetId(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BetPlaced> createBet(@RequestBody BetRequest request) {
        var command = new PlaceBetCommand(
            new MatchId(request.matchId()),
            new UserId(request.userId()),
            new TeamId(request.teamId()),
            new Money(request.amount()),
            new Coefficient(request.coefficient())
        );
        
        var event = commandService.placeBet(command);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}/win")
    public ResponseEntity<BetWon> winBet(@PathVariable Integer id) {
        var event = commandService.winBet(new BetId(id));
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}/lose")
    public ResponseEntity<BetLost> loseBet(@PathVariable Integer id) {
        var event = commandService.loseBet(new BetId(id));
        return ResponseEntity.ok(event);
    }

    public record BetRequest(
        Integer matchId,
        Integer userId,
        Integer teamId,
        java.math.BigDecimal amount,
        java.math.BigDecimal coefficient
    ) {}
} 