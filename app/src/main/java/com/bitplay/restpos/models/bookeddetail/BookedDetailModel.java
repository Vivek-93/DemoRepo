package com.bitplay.restpos.models.bookeddetail;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookedDetailModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tablenumber")
    @Expose
    private Integer tablenumber;
    @SerializedName("headcount")
    @Expose
    private Integer headcount;
    @SerializedName("guestname")
    @Expose
    private Object guestname;
    @SerializedName("phonenumber")
    @Expose
    private Integer phonenumber;
    @SerializedName("bookitems")
    @Expose
    private String bookitems;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("price")
    @Expose
    private String price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTablenumber() {
        return tablenumber;
    }

    public void setTablenumber(Integer tablenumber) {
        this.tablenumber = tablenumber;
    }

    public Integer getHeadcount() {
        return headcount;
    }

    public void setHeadcount(Integer headcount) {
        this.headcount = headcount;
    }

    public Object getGuestname() {
        return guestname;
    }

    public void setGuestname(Object guestname) {
        this.guestname = guestname;
    }

    public Integer getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBookitems() {
        return bookitems;
    }

    public void setBookitems(String bookitems) {
        this.bookitems = bookitems;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}