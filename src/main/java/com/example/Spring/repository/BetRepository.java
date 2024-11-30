package com.example.Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Spring.entity.Bet;

@Repository
public interface BetRepository extends JpaRepository<Bet, Integer> {
}