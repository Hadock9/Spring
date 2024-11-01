package com.example.Spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Spring.entity.Match;
import com.example.Spring.repository.MatchRepository;
 

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> findByGameId(Long gameId) {
        return matchRepository.findByGameId(gameId); 
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

	 
}