package com.backend.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "posts")
@JsonDeserialize(builder = Post.Builder.class)
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User userId;

    @JsonProperty("title")
    private String postTitle;

    private String category;
    private String content;
    private LocalDate time;

    public Post() {}
    private Post(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.postTitle = builder.postTitle;
        this.category = builder.category;
        this.content = builder.content;
        this.time = builder.time;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId.getUsername();
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getCategory() {
        return category;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getTime() {
        return time;
    }

    public static class Builder {
        @JsonProperty("id")
        private Long id;

        @JsonProperty("user")
        private User userId;

        @JsonProperty("title")
        private String postTitle;

        @JsonProperty("category")
        private String category;

        @JsonProperty("content")
        private String content;

        @JsonProperty("time")
        private LocalDate time;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(User userId) {
            this.userId = userId;
            return this;
        }

        public Builder setPostTitle(String postTitle) {
            this.postTitle = postTitle;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setTime(LocalDate time) {
            this.time = time;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
