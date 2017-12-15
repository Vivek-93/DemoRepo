package com.bitplay.restpos.interfaces.profiledetails;

import android.util.Log;

import com.bitplay.restpos.async.AsyncInteractor;
import com.bitplay.restpos.async.OnRequestListener;
import com.bitplay.restpos.models.login.LoginModel;
import com.bitplay.restpos.models.profiledetails.ProfileModel;
import com.bitplay.restpos.utils.AppConstants;
import com.bitplay.restpos.utils.NetworkStatus;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.activities.UserProfileActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vivek on 14-12-2017.
 */

public class ProfileDetailsPresenterImpl implements IProfileDetailsPresenter , OnRequestListener {

    private IProfileDetailsView mIProfileDetailsView;
    private UserProfileActivity mUserProfileActivity;
    private AsyncInteractor mAsyncInteractor;
    private ProfileModel mProfileModel;

    public ProfileDetailsPresenterImpl(IProfileDetailsView mIProfileDetailsView) {
        this.mIProfileDetailsView = mIProfileDetailsView;
        this.mUserProfileActivity = (UserProfileActivity) mIProfileDetailsView;
        mAsyncInteractor = new AsyncInteractor();
    }

    @Override
    public void profileDetailApiCall(int id) {

        try {
            if (NetworkStatus.checkNetworkStatus(mUserProfileActivity)) {
                Utils.showProgress(mUserProfileActivity);
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", String.valueOf(id));
                Log.d("params", "" + params.toString());
                mAsyncInteractor.validateCredentialsAsync(this, AppConstants.TAG_ID_PROFILE_DETAILS,
                        AppConstants.URL.PROFILEDETAILS.getUrl(), params);
            } else {
                Utils.showToast(mUserProfileActivity, "Please connect to internet");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestCompletion(int pid, JSONObject responseJson, JSONArray responseArray) {

    }

    @Override
    public void onRequestCompletion(int pid, String responseJson) throws JSONException {

        if (pid == AppConstants.TAG_ID_PROFILE_DETAILS) {

            if (responseJson != null) {

                Gson gson = new Gson();

                mProfileModel = gson.fromJson(responseJson, ProfileModel.class);

                mIProfileDetailsView.onProfileDetailSuccess(pid, mProfileModel);
            } else {
                //  mILoginView.onLoginError(pid, mLoginModel.getMeta().getStatus());
            }
        }

    }

    @Override
    public void onRequestCompletionError(int pid, String error) {

        if (pid == AppConstants.TAG_ID_PROFILE_DETAILS) {
            Gson gson = new Gson();
            //   mLoginErrorModel = gson.fromJson(error, LoginErrorModel.class);
            mIProfileDetailsView.onProfileDetailError(pid, mProfileModel);
        }

    }

    @Override
    public void onRequestCompletionHomeError(int pid, String error) {

    }
}
