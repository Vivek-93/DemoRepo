package com.bitplay.restpos.interfaces.searchitem;

import com.bitplay.restpos.models.searchitem.SearchItemModel;

/**
 * Created by anees on 21-12-2017.
 */

public interface ISearchItemView {

    void onSearchItemsApiSuccess(int pid, SearchItemModel[] searchItemModels);
    void onSearchItemsApiError(int pid , String error);
}
