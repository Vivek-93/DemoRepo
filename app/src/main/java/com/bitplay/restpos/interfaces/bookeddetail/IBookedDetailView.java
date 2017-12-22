package com.bitplay.restpos.interfaces.bookeddetail;

import com.bitplay.restpos.models.bookeddetail.BookedDetailModel;

/**
 * Created by Vivek on 22-12-2017.
 */

public interface IBookedDetailView {

    void onBookedDetailsApiSuccess(int pid, BookedDetailModel[] bookedDetailModel);
    void onBookedDetailsApiError(int pid, String bookedDetailModelError);
}
