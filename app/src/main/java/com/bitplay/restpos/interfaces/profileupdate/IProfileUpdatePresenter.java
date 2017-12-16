package com.bitplay.restpos.interfaces.profileupdate;

/**
 * Created by Vivek on 16-12-2017.
 */

public interface IProfileUpdatePresenter {

    void profileUpdateApiCall(int id,String name,int contactnumber,String email ,String address ,String fathername,int addharnumber,String pannumber,String selectRole);

}
