package com.example.Spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring.entity.Match;
import com.example.Spring.service.MatchService;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;
    
    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

     
    @GetMapping("/{id}")
    public List<Match> getMatchById(@PathVariable Long id) {
        return matchService.findByGameId(id);
    }
}