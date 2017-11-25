package com.bitplay.restpos.extra;

import android.text.Editable;

/**
 * Created by anees on 13-11-2017.
 */

public class UpdateItems {

    private String updateItemName;
    private Editable updateQuantity;
    private int updatePrice;

    public String getUpdateItemName() {
        return updateItemName;
    }

    public void setUpdateItemName(String updateItemName) {
        this.updateItemName = updateItemName;
    }

    public Editable getUpdateQuantity() {
        return updateQuantity;
    }

    public void setUpdateQuantity(Editable updateQuantity) {
        this.updateQuantity = updateQuantity;
    }

    public int getUpdatePrice() {
        return updatePrice;
    }

    public void setUpdatePrice(int updatePrice) {
        this.updatePrice = updatePrice;
    }
}
