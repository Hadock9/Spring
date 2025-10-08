package com.example.Spring.application.bet.commands;

import com.example.Spring.domain.bet.Bet.Coefficient;
import com.example.Spring.domain.bet.Bet.MatchId;
import com.example.Spring.domain.bet.Bet.Money;
import com.example.Spring.domain.bet.Bet.TeamId;
import com.example.Spring.domain.bet.Bet.UserId;

public record PlaceBetCommand(
    MatchId matchId,
    UserId userId,
    TeamId teamId,
    Money amount,
    Coefficient coefficient
) {} 