package com.example.mlem.Model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String id;
    private String name;
    private List<String> ingredientIds;
    private List<String> tagNames;
    private List<Ingredient> ingredients;

    public Recipe() {
    }

    public Recipe(String id, String name, List<String> ingredientIds, List<String> tagNames) {
        this.id = id;
        this.name = name;
        this.ingredientIds = ingredientIds;
        this.tagNames = tagNames;
        this.ingredients = new ArrayList<>();
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

    public List<String> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(List<String> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ingredientIds=" + ingredientIds +
                ", tagNames=" + tagNames +
                ", ingredients=" + ingredients +
                '}';
    }
}
