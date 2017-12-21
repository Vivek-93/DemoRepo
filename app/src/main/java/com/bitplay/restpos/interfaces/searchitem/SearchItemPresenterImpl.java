package com.bitplay.restpos.interfaces.searchitem;

import android.util.Log;

import com.bitplay.restpos.async.AsyncInteractor;
import com.bitplay.restpos.async.OnRequestListener;
import com.bitplay.restpos.interfaces.menucategory.IMenuCategoryView;
import com.bitplay.restpos.models.searchitem.SearchItemModel;
import com.bitplay.restpos.utils.AppConstants;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.fragments.caption.CaptionCatogeryFragment;
import com.bitplay.restpos.views.fragments.caption.CaptionSearchFragment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vivek on 21-12-2017.
 */

public class SearchItemPresenterImpl implements ISearchItemPresenter, OnRequestListener{

    private final AsyncInteractor mAsyncInteractor;
    private final ISearchItemView mISearchItemView;
    private final CaptionSearchFragment mCaptionSearchFragment;
    private SearchItemModel[] mSearchItemModel;

    public SearchItemPresenterImpl(ISearchItemView mISearchItemView) {
        this.mISearchItemView = mISearchItemView;
        this.mCaptionSearchFragment = (CaptionSearchFragment) mISearchItemView;
        mAsyncInteractor = new AsyncInteractor();
    }


    @Override
    public void searchItemsApi(int id) {

        try {

            Utils.showProgress(mCaptionSearchFragment.getActivity());
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", String.valueOf(id));
            Log.d("params", "" + params.toString());
            mAsyncInteractor.validateCredentialsAsync(this, AppConstants.TAG_ID_SEARCH_ITEMS,
                    AppConstants.URL.SEARCHITEMS.getUrl(), params);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestCompletion(int pid, JSONObject responseJson, JSONArray responseArray) {

    }

    @Override
    public void onRequestCompletion(int pid, String responseJson) throws JSONException {


        if (responseJson != null) {

            if (pid == AppConstants.TAG_ID_SEARCH_ITEMS) {
                Gson gson = new Gson();
                mSearchItemModel = gson.fromJson(responseJson, SearchItemModel[].class);
                mISearchItemView.onSearchItemsApiSuccess(pid, mSearchItemModel);
            }
        }



    }

    @Override
    public void onRequestCompletionError(int pid, String error) {

    }

    @Override
    public void onRequestCompletionHomeError(int pid, String error) {

    }
}
