package com.example.mlem.Model;

import java.util.List;

public class Recipe {
    private String id;
    private String name;
    private List<String> ingredientIds;
    private List<String> tagNames;

    public Recipe() {
    }

    public Recipe(String id, String name, List<String> ingredientIds, List<String> tagNames) {
        this.id = id;
        this.name = name;
        this.ingredientIds = ingredientIds;
        this.tagNames = tagNames;
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
}
