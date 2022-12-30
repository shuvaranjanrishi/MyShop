package com.therishideveloper.myshop.models;

/*
    Created by Shuva Ranjan Rishi on 12/29/2022
*/

public class RecommendedModel {

    private String productName, description, rating, price, type, imageUrl;

    public RecommendedModel() {
    }

    public RecommendedModel(String name, String description, String rating, String price, String type, String imageUrl) {
        this.productName = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setDiscount(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "PopularModel{" +
                "name='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                ", price='" + price + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
