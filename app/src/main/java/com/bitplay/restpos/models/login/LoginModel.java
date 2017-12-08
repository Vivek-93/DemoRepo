package com.bitplay.restpos.models.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contactnumber")
    @Expose
    private Integer contactnumber;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("fathername")
    @Expose
    private Object fathername;
    @SerializedName("addharnumber")
    @Expose
    private Integer addharnumber;
    @SerializedName("pannumber")
    @Expose
    private Object pannumber;
    @SerializedName("selectRole")
    @Expose
    private String selectRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(Integer contactnumber) {
        this.contactnumber = contactnumber;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getFathername() {
        return fathername;
    }

    public void setFathername(Object fathername) {
        this.fathername = fathername;
    }

    public Integer getAddharnumber() {
        return addharnumber;
    }

    public void setAddharnumber(Integer addharnumber) {
        this.addharnumber = addharnumber;
    }

    public Object getPannumber() {
        return pannumber;
    }

    public void setPannumber(Object pannumber) {
        this.pannumber = pannumber;
    }

    public String getSelectRole() {
        return selectRole;
    }

    public void setSelectRole(String selectRole) {
        this.selectRole = selectRole;
    }

}