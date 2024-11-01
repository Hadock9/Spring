package com.example.Spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Spring.entity.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
	@Query("SELECT n FROM News n JOIN FETCH n.game")
	List<News> findAllWithGame();
}