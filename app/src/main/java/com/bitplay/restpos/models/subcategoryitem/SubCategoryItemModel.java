package com.bitplay.restpos.models.subcategoryitem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryItemModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("itemname")
    @Expose
    private String itemname;
    @SerializedName("shortcode")
    @Expose
    private Object shortcode;
    @SerializedName("onlinedisplayname")
    @Expose
    private Object onlinedisplayname;
    @SerializedName("subcategory")
    @Expose
    private Object subcategory;
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
    @SerializedName("image_name")
    @Expose
    private Object imageName;
    @SerializedName("image_path")
    @Expose
    private Object imagePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
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

    public Object getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Object subcategory) {
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

    public Object getImageName() {
        return imageName;
    }

    public void setImageName(Object imageName) {
        this.imageName = imageName;
    }

    public Object getImagePath() {
        return imagePath;
    }

    public void setImagePath(Object imagePath) {
        this.imagePath = imagePath;
    }

}