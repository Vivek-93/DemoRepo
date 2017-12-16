package com.bitplay.restpos.interfaces.profileupdate;

import android.util.Log;

import com.bitplay.restpos.async.AsyncInteractor;
import com.bitplay.restpos.async.OnRequestListener;
import com.bitplay.restpos.models.profileupdate.ProfileUpdateModel;
import com.bitplay.restpos.models.register.RegisterModel;
import com.bitplay.restpos.utils.AppConstants;
import com.bitplay.restpos.utils.NetworkStatus;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.activities.UserProfileUpdateActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vivek on 16-12-2017.
 */

public class ProfileUpdatePresenterImpl implements IProfileUpdatePresenter, OnRequestListener {

    private IProfileUpdateView mIProfileUpdateView;
    private UserProfileUpdateActivity mUserProfileUpdateActivity;
    private AsyncInteractor mAsyncInteractor;
    private ProfileUpdateModel mProfileUpdateModel;

    public ProfileUpdatePresenterImpl( IProfileUpdateView mIProfileUpdateView) {
        this.mIProfileUpdateView = mIProfileUpdateView;
        this.mUserProfileUpdateActivity = (UserProfileUpdateActivity) mIProfileUpdateView;
        mAsyncInteractor = new AsyncInteractor();
    }



    @Override
    public void profileUpdateApiCall(int id, String name, int contactnumber, String email, String address, String fathername, int addharnumber, String pannumber, String selectRole) {

        if (NetworkStatus.checkNetworkStatus(mUserProfileUpdateActivity)) {
            //  Utils.showProgress(mRegisterActivity);
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", String.valueOf(id));
            params.put("name", name);
            params.put("contactnumber", String.valueOf(contactnumber));
            params.put("email",  email);
            params.put("address", address);
            params.put("fathername", fathername);
            params.put("addharnumber", String.valueOf(addharnumber));
            params.put("pannumber", pannumber);
            params.put("selectRole", selectRole);
            Log.d("params", "" + params.toString());
            mAsyncInteractor.validateCredentialsAsync(this, AppConstants.TAG_ID_PROFILE_UPDATE,
                    AppConstants.URL.PROFILEUPDATE.getUrl(), params);
        } else {
            Utils.showToast(mUserProfileUpdateActivity, "Please connect to internet");
        }

    }

    @Override
    public void onRequestCompletion(int pid, JSONObject responseJson, JSONArray responseArray) {

    }

    @Override
    public void onRequestCompletion(int pid, String responseJson) throws JSONException {

        if (pid == AppConstants.TAG_ID_PROFILE_UPDATE) {

            if (responseJson != null) {

                Gson gson = new Gson();

                mProfileUpdateModel= gson.fromJson(responseJson, ProfileUpdateModel.class);

                mIProfileUpdateView.onProfileUpdateSuccess(pid, mProfileUpdateModel);
            } else {
                //  mIRegisterView.onLoginError(pid, mRegisterModel.getMeta().getStatus());
            }
        }

    }

    @Override
    public void onRequestCompletionError(int pid, String error) {

        if (pid == AppConstants.TAG_ID_REGISTER) {
            Gson gson = new Gson();
            //   mLoginErrorModel = gson.fromJson(error, LoginErrorModel.class);
            mIProfileUpdateView.onProfileUpdateError(pid, mProfileUpdateModel);
        }

    }

    @Override
    public void onRequestCompletionHomeError(int pid, String error) {

    }
}
