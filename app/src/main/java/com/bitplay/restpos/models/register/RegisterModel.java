package com.bitplay.restpos.models.register;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contactnumber")
    @Expose
    private String contactnumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("fathername")
    @Expose
    private String fathername;
    @SerializedName("addharnumber")
    @Expose
    private String addharnumber;
    @SerializedName("pannumber")
    @Expose
    private String pannumber;
    @SerializedName("selectRole")
    @Expose
    private String selectRole;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getAddharnumber() {
        return addharnumber;
    }

    public void setAddharnumber(String addharnumber) {
        this.addharnumber = addharnumber;
    }

    public String getPannumber() {
        return pannumber;
    }

    public void setPannumber(String pannumber) {
        this.pannumber = pannumber;
    }

    public String getSelectRole() {
        return selectRole;
    }

    public void setSelectRole(String selectRole) {
        this.selectRole = selectRole;
    }

}
