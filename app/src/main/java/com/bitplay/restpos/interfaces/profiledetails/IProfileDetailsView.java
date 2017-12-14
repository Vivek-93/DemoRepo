package com.bitplay.restpos.interfaces.profiledetails;

import com.bitplay.restpos.models.profiledetails.ProfileModel;

/**
 * Created by Vivek on 14-12-2017.
 */

public interface IProfileDetailsView {

    void onProfileDetailSuccess(int pid, ProfileModel profileModel);

    void onProfileDetailError(int pid, ProfileModel profileErrorModel);
}
