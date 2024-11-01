package com.example.Spring.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionsList {
    @JsonProperty("questions")
    private List<Question> questions;

     
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}