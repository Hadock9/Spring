package com.example.Spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Spring.entity.Comment;
import com.example.Spring.entity.News;
import com.example.Spring.model.QuestionsList;
import com.example.Spring.repository.CommentRepository;
import com.example.Spring.repository.NewsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CommentRepository commentRepository;

    // Метод для отримання всіх новин
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    // Метод для отримання новини за ID
    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    // Метод для парсингу JSON з контенту новини
    public QuestionsList parseQuestionsFromContent(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, QuestionsList.class);
    }

    public Comment addComment(Long newsId, Comment comment) {
        News news = getNewsById(newsId);
        if (news != null) {
            comment.setNews(news);
           
            return commentRepository.save(comment);
        }
        return null;
    }
}