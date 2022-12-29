package com.therishideveloper.myshop.models;

/*
    Created by Shuva Ranjan Rishi on 12/29/2022
*/

public class PopularModel {

    private String productName,description,rating,discount,type,imageUrl;

    public PopularModel() {
    }

    public PopularModel(String name, String description, String rating, String discount, String type, String imageUrl) {
        this.productName = name;
        this.description = description;
        this.rating = rating;
        this.discount = discount;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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
                ", discount='" + discount + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
