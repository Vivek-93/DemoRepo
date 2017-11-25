package com.bitplay.restpos.extra;

import java.io.Serializable;

/**
 * Created by anees on 01-11-2017.
 */

public class BookedItems implements Serializable {

    private String itemName;
    private String quantity;
    private String price;


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
