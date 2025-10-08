package com.example.Spring.domain.bet.ports;

import java.util.List;
import java.util.Optional;

import com.example.Spring.domain.bet.Bet;
import com.example.Spring.domain.bet.Bet.BetId;

public interface BetRepository {
    Bet save(Bet bet);
    Optional<Bet> findById(BetId id);
    List<Bet> findAll();
    void delete(BetId id);
} 