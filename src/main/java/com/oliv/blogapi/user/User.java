package com.oliv.blogapi.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oliv.blogapi.articles.Article;

import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Table(name = "tb_user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull(message = "Name not can be null")
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  @NotNull(message = "Password not can be null")
  private String password;
  @Column(nullable = false)
  @NotNull(message = "Email not can be Null")
  @Email
  private String email;
  @Column(nullable = false)
  @NotNull
  private String role;

  @OneToMany(mappedBy = "authorUser", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<Article> articles;

  public User() {
  }

  public User(Long id, @NotNull(message = "Name not can be null") String name,
      @NotNull(message = "Password not can be null") String password,
      @NotNull(message = "Email not can be Null") @Email String email, @NotNull String role, Set<Article> articles) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.email = email;
    this.role = role;
    this.articles = articles;
  }

  public void encryptPassword() {
    String hash = BCrypt.hashpw(this.password, BCrypt.gensalt());
    this.password = hash;
  }

  public boolean checkPassword(String passwordToCheck) {
    return BCrypt.checkpw(passwordToCheck, this.password);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Set<Article> getArticles() {
    return articles;
  }

  public void setArticles(Set<Article> articles) {
    this.articles = articles;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((articles == null) ? 0 : articles.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((role == null) ? 0 : role.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (articles == null) {
      if (other.articles != null)
        return false;
    } else if (!articles.equals(other.articles))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (role == null) {
      if (other.role != null)
        return false;
    } else if (!role.equals(other.role))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "User [articles=" + articles + ", email=" + email + ", id=" + id + ", name=" + name + ", password="
        + password + ", role=" + role + "]";
  }

}