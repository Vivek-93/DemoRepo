package com.bitplay.restpos.interfaces.tabledetails;

/**
 * Created by Vivek on 18-12-2017.
 */

public interface ITableDetailPresenter {

    void tableDetailsApiCall(int id);

    void guestDetailsApiCall( int tablenumber, int headcount,String guestname,int phonenumber);
}
