package com.oliv.blogapi.articles;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oliv.blogapi.category.Category;
import com.oliv.blogapi.user.User;

@Entity
@Table(name = "articles")
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "articles_id")
  private Long id;
  @NotNull
  private String title;
  @ManyToMany
  private Set<Category> categories;
  @NotNull
  @Column(name = "contentOfArticle", columnDefinition = "LONGTEXT")
  private String content;
  @NotNull
  @Column(columnDefinition = "LONGTEXT")
  private String subContent;
  @ManyToOne
  @JoinColumn(name = "id_user", nullable = false)
  private User authorUser;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
  private Instant sendAt;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
  private Instant updateAt;
  @NotNull
  private String image;

  public Article() {
  }

  public Article(Long id, @NotNull String title, @NotNull String content, @NotNull String subContent, User authorUser,
      Instant sendAt, Instant updateAt, @NotNull String image) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.subContent = subContent;
    this.authorUser = authorUser;
    this.sendAt = sendAt;
    this.updateAt = updateAt;
    this.image = image;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getAuthorUser() {
    return authorUser;
  }

  public void setAuthorUser(User authorUser) {
    this.authorUser = authorUser;
  }

  public Instant getSendAt() {
    return sendAt;
  }

  public void setSendAt(Instant sendAt) {
    this.sendAt = sendAt;
  }

  public Instant getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(Instant updateAt) {
    this.updateAt = updateAt;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubContent() {
    return subContent;
  }

  public void setSubContent(String subContent) {
    this.subContent = subContent;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

}