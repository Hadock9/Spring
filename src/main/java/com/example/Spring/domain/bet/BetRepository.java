package com.example.Spring.domain.bet;

import java.util.List;
import java.util.Optional;

public interface BetRepository {
    Bet save(Bet bet);
    Optional<Bet> findById(Bet.BetId id);
    List<Bet> findAll();
    void delete(Bet.BetId id);
} 