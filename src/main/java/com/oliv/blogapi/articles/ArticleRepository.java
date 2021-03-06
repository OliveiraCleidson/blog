package com.oliv.blogapi.articles;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
  Optional<Article> findById(Long id);
}