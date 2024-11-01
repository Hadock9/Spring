package com.example.Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Spring.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    
}