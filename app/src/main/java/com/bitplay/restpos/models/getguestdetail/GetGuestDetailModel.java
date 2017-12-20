package com.bitplay.restpos.models.getguestdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetGuestDetailModel {

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
    private String guestname;
    @SerializedName("phonenumber")
    @Expose
    private Integer phonenumber;
    @SerializedName("bookitems")
    @Expose
    private Object bookitems;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("prince")
    @Expose
    private Object prince;

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

    public String getGuestname() {
        return guestname;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public Integer getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Object getBookitems() {
        return bookitems;
    }

    public void setBookitems(Object bookitems) {
        this.bookitems = bookitems;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Object getPrince() {
        return prince;
    }

    public void setPrince(Object prince) {
        this.prince = prince;
    }

}