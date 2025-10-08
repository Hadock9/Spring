package com.example.Spring.domain.bet;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bet {
    private final BetId id;
    private final MatchId matchId;
    private final UserId userId;
    private final TeamId teamId;
    private final Money amount;
    private final Coefficient coefficient;
    private BetStatus status;
    private final LocalDateTime stakeTime;

    // Value Objects
    public record BetId(Integer value) {}
    public record MatchId(Integer value) {}
    public record UserId(Integer value) {}
    public record TeamId(Integer value) {}
    public record Money(BigDecimal value) {
        public Money {
            if (value.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Amount must be positive");
            }
        }
    }
    public record Coefficient(BigDecimal value) {
        public Coefficient {
            if (value.compareTo(BigDecimal.ONE) < 0) {
                throw new IllegalArgumentException("Coefficient must be greater than or equal to 1");
            }
        }
    }

    // Domain Events
    public record BetPlaced(BetId betId, UserId userId, Money amount) {}
    public record BetWon(BetId betId, Money winningAmount) {}
    public record BetLost(BetId betId) {}

    // Constructor
    private Bet(BetId id, MatchId matchId, UserId userId, TeamId teamId, 
               Money amount, Coefficient coefficient) {
        this.id = id;
        this.matchId = matchId;
        this.userId = userId;
        this.teamId = teamId;
        this.amount = amount;
        this.coefficient = coefficient;
        this.status = BetStatus.PENDING;
        this.stakeTime = LocalDateTime.now();
    }

    // Factory method
    public static Bet place(BetId id, MatchId matchId, UserId userId, 
                          TeamId teamId, Money amount, Coefficient coefficient) {
        return new Bet(id, matchId, userId, teamId, amount, coefficient);
    }

    // Domain methods
    public BetWon win() {
        if (this.status != BetStatus.PENDING) {
            throw new IllegalStateException("Bet must be pending to be won");
        }
        this.status = BetStatus.WON;
        Money winningAmount = new Money(this.amount.value().multiply(this.coefficient.value()));
        return new BetWon(this.id, winningAmount);
    }

    public BetLost lose() {
        if (this.status != BetStatus.PENDING) {
            throw new IllegalStateException("Bet must be pending to be lost");
        }
        this.status = BetStatus.LOST;
        return new BetLost(this.id);
    }

    // Getters
    public BetId getId() { return id; }
    public MatchId getMatchId() { return matchId; }
    public UserId getUserId() { return userId; }
    public TeamId getTeamId() { return teamId; }
    public Money getAmount() { return amount; }
    public Coefficient getCoefficient() { return coefficient; }
    public BetStatus getStatus() { return status; }
    public LocalDateTime getStakeTime() { return stakeTime; }

    public enum BetStatus {
        PENDING, WON, LOST
    }
} 