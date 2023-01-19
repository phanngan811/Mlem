package com.example.mlem.Model;

import androidx.annotation.NonNull;

import java.util.List;

public class Ingredient {
    private String id;
    private String name;
    private Double price;
    private String description;
    private String imageUrl;
    private String unit;
    private List<String> tagNames;
    private List<String> cartItemIds;

    private List<CartItem> cartItems;

    public Ingredient() {
    }

    public Ingredient(String id, String name, Double price, String description, String imageUrl, String unit, List<String> tagNames, List<String> cartItemIds, List<CartItem> cartItems) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.unit = unit;
        this.tagNames = tagNames;
        this.cartItemIds = cartItemIds;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }

    public List<String> getCartItemIds() {
        return cartItemIds;
    }

    public void setCartItemIds(List<String> cartItemIds) {
        this.cartItemIds = cartItemIds;
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
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", unit='" + unit + '\'' +
                ", tagNames=" + tagNames +
                ", cartItemIds=" + cartItemIds +
                ", cartItems=" + cartItems +
                '}';
    }
}
