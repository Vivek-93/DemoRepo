package com.bitplay.restpos.interfaces.gettableguestdetail;

import android.util.Log;

import com.bitplay.restpos.async.AsyncInteractor;
import com.bitplay.restpos.async.OnRequestListener;
import com.bitplay.restpos.interfaces.tabledetails.ITableDetailView;
import com.bitplay.restpos.models.getguestdetail.GetGuestDetailModel;
import com.bitplay.restpos.utils.AppConstants;
import com.bitplay.restpos.utils.NetworkStatus;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.activities.TableDetailsActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vivek on 20-12-2017.
 */

public class GetGuestDetailPresenterImpl implements IGetGuestDetailPresenter ,OnRequestListener{

    private IGetGuestDetailView mIGetGuestDetailView;
    private TableDetailsActivity mTableDetailsActivity;
    private AsyncInteractor mAsyncInteractor;
    private GetGuestDetailModel mGetGuestDetailModel;

    public GetGuestDetailPresenterImpl(IGetGuestDetailView mIGetGuestDetailView) {
        this.mIGetGuestDetailView = mIGetGuestDetailView;
        this.mTableDetailsActivity = (TableDetailsActivity) mIGetGuestDetailView;
        mAsyncInteractor = new AsyncInteractor();
    }


    @Override
    public void getGuestDetailApiCall(int id) {

        if (NetworkStatus.checkNetworkStatus(mTableDetailsActivity)) {
         //   Utils.showProgress(mTableDetailsActivity);
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", String.valueOf(id));
            Log.d("params", "" + params.toString());
            mAsyncInteractor.validateCredentialsAsync(this, AppConstants.TAG_ID_GET_GUEST_DETAILS,
                    AppConstants.URL.GETGUESTDETAIL.getUrl(), params);
        } else {
            Utils.showToast(mTableDetailsActivity, "Please connect to internet");
        }

    }

    @Override
    public void onRequestCompletion(int pid, JSONObject responseJson, JSONArray responseArray) {

    }

    @Override
    public void onRequestCompletion(int pid, String responseJson) throws JSONException {

        if (pid == AppConstants.TAG_ID_GET_GUEST_DETAILS) {

            try {
                if (responseJson != null) {
                    Gson gson = new Gson();

                    mGetGuestDetailModel = gson.fromJson(responseJson,GetGuestDetailModel.class);
                    mIGetGuestDetailView.onGetGuestDetailSuccess(pid, mGetGuestDetailModel);
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
        if (pid == AppConstants.TAG_ID_GET_GUEST_DETAILS) {
            Gson gson = new Gson();
            mGetGuestDetailModel = gson.fromJson(error, GetGuestDetailModel.class);
            mIGetGuestDetailView.onGetGuestDetailError(pid, error);
        }

    }

    @Override
    public void onRequestCompletionHomeError(int pid, String error) {

    }
}
