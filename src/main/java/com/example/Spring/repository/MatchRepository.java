package com.example.Spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Spring.entity.Match;

 

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByGameId(Long gameId);
}