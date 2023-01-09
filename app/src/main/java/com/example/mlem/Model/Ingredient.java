package com.example.mlem.Model;

import java.util.List;

public class Ingredient {
    private String id;
    private String name;
    private Double price;
    private List<String> tagNames;

    public Ingredient() {
    }

    public Ingredient(String id, String name, Double price, List<String> tagNames) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }
}
