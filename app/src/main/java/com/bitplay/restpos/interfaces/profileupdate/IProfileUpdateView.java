package com.bitplay.restpos.interfaces.profileupdate;

import com.bitplay.restpos.models.profileupdate.ProfileUpdateModel;

/**
 * Created by Vivek on 16-12-2017.
 */

public interface IProfileUpdateView {

    void onProfileUpdateSuccess(int pid, ProfileUpdateModel profileUpdateModel);

    void onProfileUpdateError(int pid, ProfileUpdateModel profileUpdateErrorModel);
}
