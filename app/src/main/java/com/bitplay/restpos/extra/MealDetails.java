package com.bitplay.restpos.extra;

import java.io.Serializable;

/**
 * Created by anees on 25-10-2017.
 */

public class MealDetails implements Serializable {

    private String id;
    private String itemName;
    private String shortCode;
    private String onlineDisplayName;
    private String category;
    private String price;
    private String minimumPreparationTime;
    private String hSNCode;
    private String description;
    private String available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getOnlineDisplayName() {
        return onlineDisplayName;
    }

    public void setOnlineDisplayName(String onlineDisplayName) {
        this.onlineDisplayName = onlineDisplayName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMinimumPreparationTime() {
        return minimumPreparationTime;
    }

    public void setMinimumPreparationTime(String minimumPreparationTime) {
        this.minimumPreparationTime = minimumPreparationTime;
    }

    public String gethSNCode() {
        return hSNCode;
    }

    public void sethSNCode(String hSNCode) {
        this.hSNCode = hSNCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "{" + id + "::" + itemName + "" + shortCode + "" + onlineDisplayName +  "" + category +  "" + price + ""+ minimumPreparationTime + "" + hSNCode
                + ""+ description + "" + available +  "}";
    }

}
