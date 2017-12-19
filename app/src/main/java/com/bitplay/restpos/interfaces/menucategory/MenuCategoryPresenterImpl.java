package com.bitplay.restpos.interfaces.menucategory;

import android.util.Log;

import com.bitplay.restpos.async.AsyncInteractor;
import com.bitplay.restpos.async.OnRequestListener;
import com.bitplay.restpos.models.menucategory.MenuCategoryModel;
import com.bitplay.restpos.utils.AppConstants;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.fragments.caption.CaptionCatogeryFragment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vivek on 19-12-2017.
 */

public class MenuCategoryPresenterImpl implements IMenuCategoryPresenter,OnRequestListener{

    private final AsyncInteractor mAsyncInteractor;
    private final IMenuCategoryView mIMenuCategoryView;
    private final CaptionCatogeryFragment mCaptionCatogeryFragment;

    private MenuCategoryModel[] mMenuCategoryModel;

    public MenuCategoryPresenterImpl(IMenuCategoryView mIMenuCategoryView) {
        this.mIMenuCategoryView = mIMenuCategoryView;
        this.mCaptionCatogeryFragment = (CaptionCatogeryFragment) mIMenuCategoryView;
        mAsyncInteractor = new AsyncInteractor();
    }


    @Override
    public void getMenuCategoryApi() {

        Utils.showProgress(mCaptionCatogeryFragment.getActivity());
        mAsyncInteractor.validateCredentialsAsync(this, AppConstants.TAG_ID_MENU_CATEGORY,
                AppConstants.URL.MENUCATEGORY.getUrl());


    }

    @Override
    public void onRequestCompletion(int pid, JSONObject responseJson, JSONArray responseArray) {

    }

    @Override
    public void onRequestCompletion(int pid, String responseJson) throws JSONException {

        if (responseJson != null) {

            Log.d("MenuPresenter","comming1");
                if (pid == AppConstants.TAG_ID_MENU_CATEGORY) {
                    Gson gson = new Gson();
                    Log.d("MenuPresenter","comming2");
                    mMenuCategoryModel = gson.fromJson(responseJson, MenuCategoryModel[].class);
                    Log.d("MenuPresenter","comming3");
                    mIMenuCategoryView.getMenuCategoryApiSuccess(pid, mMenuCategoryModel);
                }

        }

    }

    @Override
    public void onRequestCompletionError(int pid, String error) {

        try {
            if (pid == AppConstants.TAG_ID_MENU_CATEGORY) {
                mIMenuCategoryView.getMenuCategoryApiError(pid, error);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onRequestCompletionHomeError(int pid, String error) {

    }
}
