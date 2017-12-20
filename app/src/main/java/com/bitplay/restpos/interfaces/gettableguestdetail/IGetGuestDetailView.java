package com.bitplay.restpos.interfaces.gettableguestdetail;

import com.bitplay.restpos.models.getguestdetail.GetGuestDetailModel;

/**
 * Created by anees on 20-12-2017.
 */

public interface IGetGuestDetailView {

    void onGetGuestDetailSuccess(int pid, GetGuestDetailModel getGuestDetailModel);
    void onGetGuestDetailError(int pid, String getGuestDetailError);
}
