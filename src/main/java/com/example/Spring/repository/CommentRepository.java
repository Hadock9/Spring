package com.example.Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Spring.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
