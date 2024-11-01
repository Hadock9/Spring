package com.example.Spring.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring.entity.Comment;
import com.example.Spring.entity.News;
import com.example.Spring.service.NewsService;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        News news = newsService.getNewsById(id);
        if (news != null) {
            return ResponseEntity.ok(news);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long id, 
    @RequestParam(defaultValue = "Anonymous") String author, @RequestParam String content, @RequestParam(defaultValue = "/img/User-Default.svg") String picture) {
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setContent(content);
        comment.setPicture(picture);
        comment.setPublishDate(new Date());  
       
        
        Comment savedComment = newsService.addComment(id, comment);
        if (savedComment != null) {
            return ResponseEntity.ok(savedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsByNewsId(@PathVariable Long id) {
        News news = newsService.getNewsById(id);
        if (news != null) {
            List<Comment> comments = news.getComments();
            return ResponseEntity.ok(comments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}