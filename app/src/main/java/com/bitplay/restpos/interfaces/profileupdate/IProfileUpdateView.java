package com.bitplay.restpos.interfaces.profileupdate;

/**
 * Created by Vivek on 16-12-2017.
 */

public interface IProfileUpdateView {

    void onProfileUpdateSuccess(int pid, String profileUpdateModel);

    void onProfileUpdateError(int pid, String profileUpdateErrorModel);
}
