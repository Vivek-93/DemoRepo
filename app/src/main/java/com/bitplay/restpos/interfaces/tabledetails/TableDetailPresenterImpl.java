package com.bitplay.restpos.interfaces.tabledetails;

import android.util.Log;

import com.bitplay.restpos.async.AsyncInteractor;
import com.bitplay.restpos.async.OnRequestListener;
import com.bitplay.restpos.models.register.RegisterModel;
import com.bitplay.restpos.models.tabledetails.TableDetailModel;
import com.bitplay.restpos.utils.AppConstants;
import com.bitplay.restpos.utils.NetworkStatus;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.activities.MainActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vivek on 18-12-2017.
 */

public class TableDetailPresenterImpl implements ITableDetailPresenter, OnRequestListener {

    private ITableDetailView mITableDetailView;
    private MainActivity mMainActivity;
    private AsyncInteractor mAsyncInteractor;
    private TableDetailModel mTableDetailModel;

    public TableDetailPresenterImpl(ITableDetailView mITableDetailView) {
        this.mITableDetailView = mITableDetailView;
        this.mMainActivity = (MainActivity) mITableDetailView;
        mAsyncInteractor = new AsyncInteractor();
    }


    @Override
    public void tableDetailsApiCall(int id) {


        if (NetworkStatus.checkNetworkStatus(mMainActivity)) {
            Utils.showProgress(mMainActivity);
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", String.valueOf(id));
            Log.d("params", "" + params.toString());
            mAsyncInteractor.validateCredentialsAsync(this, AppConstants.TAG_ID_TABLE_DETAILS,
                    AppConstants.URL.TABLEDETAILS.getUrl(), params);
        } else {
            Utils.showToast(mMainActivity, "Please connect to internet");
        }

    }

    @Override
    public void onRequestCompletion(int pid, JSONObject responseJson, JSONArray responseArray) {

    }

    @Override
    public void onRequestCompletion(int pid, String responseJson) throws JSONException {

        if (pid == AppConstants.TAG_ID_TABLE_DETAILS) {

            try {
                if (responseJson != null) {
                    Gson gson = new Gson();
                    Log.d("TablePresenterImpl", "mes3" + AppConstants.TAG_ID_TABLE_DETAILS);
                 //   JSONArray jsonArray = gson.fromJson(responseJson, TableDetailModel.class);
                    mTableDetailModel = gson.fromJson(responseJson, TableDetailModel.class);
                    Log.d("TablePresenterImpl", "mes4" + AppConstants.TAG_ID_TABLE_DETAILS);
                    mITableDetailView.onTableDetailsSuccess(pid, mTableDetailModel);
                } else {

                    //  mIRegisterView.onLoginError(pid, mRegisterModel.getMeta().getStatus());

                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onRequestCompletionError(int pid, String error) {

        if (pid == AppConstants.TAG_ID_TABLE_DETAILS) {
            Gson gson = new Gson();
            mTableDetailModel = gson.fromJson(error, TableDetailModel.class);
            mITableDetailView.onTableDetailsError(pid, mTableDetailModel);
        }

    }

    @Override
    public void onRequestCompletionHomeError(int pid, String error) {

    }
}
