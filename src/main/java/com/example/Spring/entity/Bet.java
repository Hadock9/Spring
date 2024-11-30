package com.example.Spring.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "match_id", nullable = true)
    private Integer matchId;

    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "Coef", nullable = true, precision = 5, scale = 2)
    private BigDecimal coefficient;

    @Column(name = "stake_time", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime stakeTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private BetStatus status;

    @Column(name = "user_id", nullable = true)
    private Integer userId;

    @Column(name = "team_id", nullable = true)
    private Integer teamId;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public LocalDateTime getStakeTime() {
        return stakeTime;
    }

    public void setStakeTime(LocalDateTime stakeTime) {
        this.stakeTime = stakeTime;
    }

    public BetStatus getStatus() {
        return status;
    }

    public void setStatus(BetStatus status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    // Enum for status
    public enum BetStatus {
			 pending, won, lost
    }
}