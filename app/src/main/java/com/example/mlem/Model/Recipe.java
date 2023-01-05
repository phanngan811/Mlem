package com.example.mlem.Model;

public class Recipe {
    private String id;
    private String[] ingredientIds;
    private String tagId;

    public Recipe(String id, String[] ingredientIds, String tagId) {
        this.id = id;
        this.ingredientIds = ingredientIds;
        this.tagId = tagId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(String[] ingredientIds) {
        this.ingredientIds = ingredientIds;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
