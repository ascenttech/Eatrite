package com.healthyfood.eatrite.data;

/**
 * Created by ADMIN on 23-10-2015.
 */
public class AllDishesData {

    String id,description,price,quantity,image;

    public AllDishesData(String id, String description, String price, String quantity, String image) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
