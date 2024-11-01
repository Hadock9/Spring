package com.example.Spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Spring.entity.Game;
import com.example.Spring.entity.Match;
import com.example.Spring.entity.News;
import com.example.Spring.model.QuestionsList;
import com.example.Spring.service.GameService;
import com.example.Spring.service.MatchService;
import com.example.Spring.service.NewsService;

@Controller
public class WebController {

    private final GameService gameService;
    private final MatchService matchService;
    private final NewsService newsService;

    @Autowired
    public WebController(GameService gameService, MatchService matchService, NewsService newsService) {
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
        return "matches";  
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
            // Обробка виключення 
            e.printStackTrace();
        }

        return "newsDetail";  
    }
}