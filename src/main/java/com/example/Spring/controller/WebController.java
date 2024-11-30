package com.example.Spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Spring.entity.Bet;
import com.example.Spring.entity.Game;
import com.example.Spring.entity.Match;
import com.example.Spring.entity.News;
import com.example.Spring.model.QuestionsList;
import com.example.Spring.service.BetService;
import com.example.Spring.service.GameService;
import com.example.Spring.service.MatchService;
import com.example.Spring.service.NewsService;

@Controller
public class WebController {

    private final BetService betService;
    private final GameService gameService;
    private final MatchService matchService;
    private final NewsService newsService;

    // Конструктор із усіма залежностями
    @Autowired
    public WebController(BetService betService, GameService gameService, MatchService matchService, NewsService newsService) {
        this.betService = betService;
        this.gameService = gameService;
        this.matchService = matchService;
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("title", "Головна");
        return "home";  
    }

    @GetMapping("/games")
    public String getGames(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        model.addAttribute("title", "Список Ігор");
        return "games"; 
    }

    @GetMapping("/matches")
    public String showMatches(Model model) {
        List<Match> matches = matchService.getAllMatches();  
        model.addAttribute("matches", matches);
        model.addAttribute("title", "Список Матчів"); 
        return "matches";  
    }

    @GetMapping("/matches/{id}")
    public String getMatchesByGameId(@PathVariable Long id, Model model) {
        List<Match> matches = matchService.findByGameId(id);  
        model.addAttribute("matches", matches);
        model.addAttribute("title", "Матчі за Грою");
        return "matches";  
    }

    @GetMapping("/match/{matchId}")
    public String getMatchDetails(@PathVariable int matchId, Model model) {
        Match match = matchService.getMatchById(matchId);
        model.addAttribute("match", match);  
        model.addAttribute("title", "Деталі Матчу");
        return "matchDetails";  
    }

    @GetMapping("/news")
    public String getNews(Model model) {
        List<News> newsList = newsService.getAllNews();   
        model.addAttribute("newsList", newsList);  
        model.addAttribute("title", "Список Новин");   
        return "news";   
    }

    @GetMapping("/news/{id}")
    public String getNewsById(@PathVariable Long id, Model model) {
        News news = newsService.getNewsById(id);
        
        try {
            QuestionsList questionsList = newsService.parseQuestionsFromContent(news.getContent());
            model.addAttribute("news", news);
            model.addAttribute("questionsList", questionsList);
        } catch (Exception e) {
            e.printStackTrace(); // Обробка виключення
        }

        model.addAttribute("title", "Деталі Новини");
        return "newsDetail";  
    }

    @GetMapping("/bets")
    public String showBets(Model model) {
        List<Bet> bets = betService.getAllBets();  
        model.addAttribute("bets", bets);
        model.addAttribute("title", "Список Ставок");
        return "bets"; 
    }
}