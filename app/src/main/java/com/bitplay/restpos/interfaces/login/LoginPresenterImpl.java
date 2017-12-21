package com.bitplay.restpos.interfaces.login;

import android.util.Log;

import com.bitplay.restpos.async.AsyncInteractor;
import com.bitplay.restpos.async.OnRequestListener;
import com.bitplay.restpos.models.login.LoginModel;
import com.bitplay.restpos.utils.AppConstants;
import com.bitplay.restpos.utils.NetworkStatus;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.activities.LoginActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by vivek on 7/4/2017.
 */

public class LoginPresenterImpl implements ILoginPresenter, OnRequestListener {

    private ILoginView mILoginView;
    private LoginActivity mLoginActivity;
    private AsyncInteractor mAsyncInteractor;
    private LoginModel mLoginModel;
    private String mLoginErrorModel;

    public LoginPresenterImpl(ILoginView mILoginView) {
        this.mILoginView = mILoginView;
        this.mLoginActivity = (LoginActivity) mILoginView;
        mAsyncInteractor = new AsyncInteractor();
    }

    @Override
    public void loginApiCall(String email, String password) {
        if (NetworkStatus.checkNetworkStatus(mLoginActivity)) {
            Utils.showProgress(mLoginActivity);
            Map<String, String> params = new HashMap<String, String>();
            params.put("email",  email);
            params.put("password", password);
            Log.d("params", "" + params.toString());
            mAsyncInteractor.validateCredentialsAsync(this, AppConstants.TAG_ID_LOGIN,
                    AppConstants.URL.LOGIN.getUrl(), params);
        } else {
            Utils.showToast(mLoginActivity, "Please connect to internet");
        }
    }



    @Override
    public void onRequestCompletion(int pid, JSONObject responseJson, JSONArray responseArray) {

    }

    @Override
    public void onRequestCompletion(int pid, String responseJson) throws JSONException {

        if (pid == AppConstants.TAG_ID_LOGIN) {

            if (responseJson != null) {
                Gson gson = new Gson();
                mLoginModel = gson.fromJson(responseJson, LoginModel.class);
                mILoginView.onLoginSuccess(pid, mLoginModel);
            } else {
                //  mILoginView.onLoginError(pid, mLoginModel.getMeta().getStatus());
            }
        }

    }

    @Override
    public void onRequestCompletionError(int pid, String error) {
        if (pid == AppConstants.TAG_ID_LOGIN) {
            Gson gson = new Gson();
            mLoginErrorModel = gson.toJson(error);
            mILoginView.onLoginError(pid, mLoginErrorModel);
        }
    }

    @Override
    public void onRequestCompletionHomeError(int pid, String error) {

    }


}

