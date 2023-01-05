package com.example.mlem.Model;

public class Ingredient {
    private String id;
    private String name;
    private Double price;
    private String tagId;

    public Ingredient(String id, String name, Double price, String tagId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.tagId = tagId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
