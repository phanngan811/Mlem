package com.example.mlem.Model;

import java.util.List;

public class Blog {
    private String id;
    private String title;
    private String content;
    private String author;
    private String recipeId;
    private List<String> tagNames;

    public Blog() {
    }

    public Blog(String id, String title, String content, String author, String recipeId, List<String> tagNames) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.recipeId = recipeId;
        this.tagNames = tagNames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }
}

