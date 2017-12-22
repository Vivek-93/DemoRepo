package com.bitplay.restpos.interfaces.menucategory;

/**
 * Created by Vivek on 19-12-2017.
 */

public interface IMenuCategoryPresenter {

    void getMenuCategoryApi();
    void getSubCategoryApi(String category);
    void getSubCategoryItemApi(String subcategory);
    void bookedOrderApi(int tablenumber,String bookitems,int quantity,float price);

}
