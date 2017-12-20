package com.bitplay.restpos.models.subcategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubcategoryModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("itemname")
    @Expose
    private Object itemname;
    @SerializedName("shortcode")
    @Expose
    private Object shortcode;
    @SerializedName("onlinedisplayname")
    @Expose
    private Object onlinedisplayname;
    @SerializedName("subcategory")
    @Expose
    private String subcategory;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("minimumpreparationtime")
    @Expose
    private Object minimumpreparationtime;
    @SerializedName("hsncode")
    @Expose
    private Object hsncode;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("available")
    @Expose
    private Object available;
    @SerializedName("category")
    @Expose
    private Object category;
    @SerializedName("mealtype")
    @Expose
    private Object mealtype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getItemname() {
        return itemname;
    }

    public void setItemname(Object itemname) {
        this.itemname = itemname;
    }

    public Object getShortcode() {
        return shortcode;
    }

    public void setShortcode(Object shortcode) {
        this.shortcode = shortcode;
    }

    public Object getOnlinedisplayname() {
        return onlinedisplayname;
    }

    public void setOnlinedisplayname(Object onlinedisplayname) {
        this.onlinedisplayname = onlinedisplayname;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getMinimumpreparationtime() {
        return minimumpreparationtime;
    }

    public void setMinimumpreparationtime(Object minimumpreparationtime) {
        this.minimumpreparationtime = minimumpreparationtime;
    }

    public Object getHsncode() {
        return hsncode;
    }

    public void setHsncode(Object hsncode) {
        this.hsncode = hsncode;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getAvailable() {
        return available;
    }

    public void setAvailable(Object available) {
        this.available = available;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public Object getMealtype() {
        return mealtype;
    }

    public void setMealtype(Object mealtype) {
        this.mealtype = mealtype;
    }

}