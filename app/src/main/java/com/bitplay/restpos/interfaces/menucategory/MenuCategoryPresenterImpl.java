package com.bitplay.restpos.interfaces.menucategory;

import android.util.Log;

import com.bitplay.restpos.async.AsyncInteractor;
import com.bitplay.restpos.async.OnRequestListener;
import com.bitplay.restpos.models.menucategory.MenuCategoryModel;
import com.bitplay.restpos.models.subcategory.SubcategoryModel;
import com.bitplay.restpos.models.subcategoryitem.SubCategoryItemModel;
import com.bitplay.restpos.utils.AppConstants;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.fragments.caption.CaptionCatogeryFragment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vivek on 19-12-2017.
 */

public class MenuCategoryPresenterImpl implements IMenuCategoryPresenter, OnRequestListener {

    private final AsyncInteractor mAsyncInteractor;
    private final IMenuCategoryView mIMenuCategoryView;
    private final CaptionCatogeryFragment mCaptionCatogeryFragment;

    private MenuCategoryModel[] mMenuCategoryModel;
    private SubcategoryModel[] mSubcategoryModel;
    private SubCategoryItemModel[] mSubcategoryItemModel;
    private String mBookedOrder;

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
    public void getSubCategoryApi(String category) {
        try {

            Utils.showProgress(mCaptionCatogeryFragment.getActivity());
            Map<String, String> params = new HashMap<String, String>();
            params.put("category", category);
            Log.d("params", "" + params.toString());
            mAsyncInteractor.validateCredentialsAsync(this, AppConstants.TAG_ID_SUB_CATEGORY,
                    AppConstants.URL.SUBCATEGORY.getUrl(), params);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getSubCategoryItemApi(String subcategory) {

        try {

            Utils.showProgress(mCaptionCatogeryFragment.getActivity());
            Map<String, String> params = new HashMap<String, String>();
            params.put("subcategory", subcategory);
            Log.d("params", "" + params.toString());
            mAsyncInteractor.validateCredentialsAsync(this, AppConstants.TAG_ID_SUB_CATEGORY_ITEMS,
                    AppConstants.URL.SUBCATEGORYITEMS.getUrl(), params);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void bookedOrderApi(int tablenumber, String bookitems, int quantity ,float price) {

        try {
            Utils.showProgress(mCaptionCatogeryFragment.getActivity());
            Map<String, String> params = new HashMap<String, String>();
            params.put("tablenumber", String.valueOf(tablenumber));
            params.put("bookitems", bookitems);
            params.put("quantity", String.valueOf(quantity));
            params.put("price", String.valueOf(price));
            Log.d("params", "" + params.toString());
            mAsyncInteractor.validateCredentialsAsync(this, AppConstants.TAG_ID_BOOKED_ORDER,
                    AppConstants.URL.BOOKEDORDER.getUrl(), params);

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

            if (pid == AppConstants.TAG_ID_MENU_CATEGORY) {
                Gson gson = new Gson();
                mMenuCategoryModel = gson.fromJson(responseJson, MenuCategoryModel[].class);
                mIMenuCategoryView.getMenuCategoryApiSuccess(pid, mMenuCategoryModel);
            } else if (pid == AppConstants.TAG_ID_SUB_CATEGORY) {
                Gson gson = new Gson();
                mSubcategoryModel = gson.fromJson(responseJson, SubcategoryModel[].class);
                mIMenuCategoryView.onGetSubCategorySuccess(pid, mSubcategoryModel);

            } else if (pid == AppConstants.TAG_ID_SUB_CATEGORY_ITEMS) {
                Gson gson = new Gson();
                mSubcategoryItemModel = gson.fromJson(responseJson, SubCategoryItemModel[].class);
                mIMenuCategoryView.onGetSubCategoryItemSuccess(pid, mSubcategoryItemModel);

            } else if (pid == AppConstants.TAG_ID_BOOKED_ORDER) {
                Gson gson = new Gson();
                mBookedOrder= gson.toJson(responseJson);
                mIMenuCategoryView.onOrderBookedSuccess(pid, mBookedOrder);

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
