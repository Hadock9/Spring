package com.example.Spring.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchID;

    private String place;

    private String season;

    private LocalDate vsDate;

    private LocalTime time;

    // Команда 1
    @ManyToOne
    @JoinColumn(name = "team1Id", referencedColumnName = "teamID", nullable = false)
    private Team team1;

    // Команда 2
    @ManyToOne
    @JoinColumn(name = "team2Id", referencedColumnName = "teamID", nullable = false)
    private Team team2;

    private Integer team1Score;

    private Integer team2Score;

    private int gameId;

    // Нові поля для коефіцієнтів
    private Double team1Coef;
    private Double team2Coef;

    // Геттери і сеттери
    public int getMatchId() {
        return matchID;
    }

    public void setMatchId(int matchID) {
        this.matchID = matchID;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public LocalDate getVsDate() {
        return vsDate;
    }

    public void setVsDate(LocalDate vsDate) {
        this.vsDate = vsDate;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Integer getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(Integer team1Score) {
        this.team1Score = team1Score;
    }

    public Integer getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(Integer team2Score) {
        this.team2Score = team2Score;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Double getTeam1Coef() {
        return team1Coef;
    }

    public void setTeam1Coef(Double team1Coef) {
        this.team1Coef = team1Coef;
    }

    public Double getTeam2Coef() {
        return team2Coef;
    }

    public void setTeam2Coef(Double team2Coef) {
        this.team2Coef = team2Coef;
    }
}