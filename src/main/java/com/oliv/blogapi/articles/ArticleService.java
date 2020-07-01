package com.oliv.blogapi.articles;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  public Page<Article> findAll(int page, int size) {
    Pageable pageToSend = PageRequest.of(page <= 0 ? 0 : --page, size, Sort.by("sendAt").descending());
    return articleRepository.findAll(pageToSend);
  }

  public Article createArticle(Article article) {
    article.setSendAt(Instant.now());
    article.setUpdateAt(Instant.now());
    return articleRepository.save(article);
  }

  public Optional<Article> findById(Long id) {
    return articleRepository.findById(id);
  }
}