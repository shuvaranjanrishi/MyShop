package com.therishideveloper.myshop.models;

/*
    Created by Shuva Ranjan Rishi on 12/30/2022
*/

public class CategoryNav {

    private String name,description,discount,imageUrl;

    public CategoryNav() {
    }

    public CategoryNav(String name, String description, String discount, String imageUrl) {
        this.name = name;
        this.description = description;
        this.discount = discount;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "CategoryNav{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", discount='" + discount + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
