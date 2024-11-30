package com.example.Spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Spring.entity.Bet;
import com.example.Spring.repository.BetRepository;

@Service
public class BetService {

    @Autowired
    private BetRepository betRepository;

    // Отримати всі ставки
    public List<Bet> getAllBets() {
        return betRepository.findAll();
    }

    // Отримати ставку за ID
    public Optional<Bet> findByBetId(Integer id) {
        return betRepository.findById(id);
    }

    // Створити нову ставку
    public Bet createBet(Bet bet) {
        return betRepository.save(bet);
    }

    // Оновити існуючу ставку
    public Bet updateBet(Integer id, Bet betDetails) {
        Bet existingBet = betRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bet not found"));

        existingBet.setMatchId(betDetails.getMatchId());
        existingBet.setAmount(betDetails.getAmount());
        existingBet.setCoefficient(betDetails.getCoefficient());
        existingBet.setStakeTime(betDetails.getStakeTime());
        existingBet.setStatus(betDetails.getStatus());
        existingBet.setUserId(betDetails.getUserId());
        existingBet.setTeamId(betDetails.getTeamId());

        return betRepository.save(existingBet);
    }

    // Видалити ставку
    public void deleteBet(Integer id) {
        betRepository.deleteById(id);
    }
}