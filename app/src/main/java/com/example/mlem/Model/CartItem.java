package com.example.mlem.Model;

import androidx.annotation.NonNull;

public class CartItem {
    private String id;
    private String ingredientId;
    private Double amount;
    private String userId;
    private Ingredient ingredient;

    public CartItem() {
    }

    public CartItem(String id, String ingredientId, Double amount, String userId, Ingredient ingredient) {
        this.id = id;
        this.ingredientId = ingredientId;
        this.amount = amount;
        this.userId = userId;
        this.ingredient = ingredient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @NonNull
    @Override
    public String toString() {
        return "CartItem{" +
                "id='" + id + '\'' +
                ", ingredientId='" + ingredientId + '\'' +
                ", amount=" + amount +
                ", userId='" + userId + '\'' +
                ", ingredient=" + ingredient +
                '}';
    }
}
