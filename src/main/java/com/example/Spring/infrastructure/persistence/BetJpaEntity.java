package com.example.Spring.infrastructure.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.Spring.domain.bet.Bet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stake")
public class BetJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "match_id", nullable = true)
    private Integer matchId;

    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "Coef", nullable = true, precision = 5, scale = 2)
    private BigDecimal coefficient;

    @Column(name = "stake_time", nullable = true)
    private LocalDateTime stakeTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Bet.BetStatus status;

    @Column(name = "user_id", nullable = true)
    private Integer userId;

    @Column(name = "team_id", nullable = true)
    private Integer teamId;

    public Bet toDomain() {
        return Bet.place(
            new Bet.BetId(id),
            new Bet.MatchId(matchId),
            new Bet.UserId(userId),
            new Bet.TeamId(teamId),
            new Bet.Money(amount),
            new Bet.Coefficient(coefficient)
        );
    }

    public static BetJpaEntity fromDomain(Bet bet) {
        var entity = new BetJpaEntity();
        entity.id = bet.getId().value();
        entity.matchId = bet.getMatchId().value();
        entity.userId = bet.getUserId().value();
        entity.teamId = bet.getTeamId().value();
        entity.amount = bet.getAmount().value();
        entity.coefficient = bet.getCoefficient().value();
        entity.status = bet.getStatus();
        entity.stakeTime = bet.getStakeTime();
        return entity;
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getMatchId() { return matchId; }
    public void setMatchId(Integer matchId) { this.matchId = matchId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getCoefficient() { return coefficient; }
    public void setCoefficient(BigDecimal coefficient) { this.coefficient = coefficient; }
    public LocalDateTime getStakeTime() { return stakeTime; }
    public void setStakeTime(LocalDateTime stakeTime) { this.stakeTime = stakeTime; }
    public Bet.BetStatus getStatus() { return status; }
    public void setStatus(Bet.BetStatus status) { this.status = status; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getTeamId() { return teamId; }
    public void setTeamId(Integer teamId) { this.teamId = teamId; }
} 