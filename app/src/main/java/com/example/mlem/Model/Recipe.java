package com.example.mlem.Model;

import androidx.annotation.NonNull;

import java.util.List;

public class Recipe {
    private String id;
    private String name;
    private List<String> cartItemIds;
    private List<String> tagNames;
    private List<String> steps;
    private String imageUrl;
    private String difficulty;
    private String duration;
    private Double rating;

    private List<CartItem> cartItems;

    public Recipe() {
    }

    public Recipe(String id, String name, List<String> cartItemIds, List<String> tagNames, List<String> steps, String imageUrl, String difficulty, String duration, Double rating, List<CartItem> cartItems) {
        this.id = id;
        this.name = name;
        this.cartItemIds = cartItemIds;
        this.tagNames = tagNames;
        this.steps = steps;
        this.imageUrl = imageUrl;
        this.difficulty = difficulty;
        this.duration = duration;
        this.rating = rating;
        this.cartItems = cartItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCartItemIds() {
        return cartItemIds;
    }

    public void setCartItemIds(List<String> cartItemIds) {
        this.cartItemIds = cartItemIds;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cartItemIds=" + cartItemIds +
                ", tagNames=" + tagNames +
                ", steps=" + steps +
                ", imageUrl='" + imageUrl + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", duration='" + duration + '\'' +
                ", rating=" + rating +
                ", cartItems=" + cartItems +
                '}';
    }
}
