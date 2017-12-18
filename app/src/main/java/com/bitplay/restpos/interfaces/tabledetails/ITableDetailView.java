package com.bitplay.restpos.interfaces.tabledetails;

import com.bitplay.restpos.models.tabledetails.TableDetailModel;

/**
 * Created by Vivek on 18-12-2017.
 */

public interface ITableDetailView {


    void onTableDetailsSuccess(int pid, TableDetailModel tableDetailModel);

    void onTableDetailsError(int pid, TableDetailModel tableDetailErrorModel);
}
