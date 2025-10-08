package com.example.Spring.application.bet;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Spring.domain.bet.Bet;
import com.example.Spring.domain.bet.Bet.BetId;
import com.example.Spring.domain.bet.ports.BetRepository;

@Service
@Transactional(readOnly = true)
public class BetQueryService {
    private final BetRepository betRepository;

    public BetQueryService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public List<Bet> getAllBets() {
        return betRepository.findAll();
    }

    public Optional<Bet> getBetById(BetId id) {
        return betRepository.findById(id);
    }
} 