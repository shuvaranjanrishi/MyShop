package com.therishideveloper.myshop.models;

/*
    Created by Shuva Ranjan Rishi on 12/30/2022
*/

public class ViewAllModel {

    private String name,description,type,rating,price,imageUrl;

    public ViewAllModel() {
    }

    public ViewAllModel(String name, String description, String type, String rating, String price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.rating = rating;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ViewAllModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", rating='" + rating + '\'' +
                ", price='" + price + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
