package com.bitplay.restpos.interfaces.register;

import android.util.Log;

import com.bitplay.restpos.async.AsyncInteractor;
import com.bitplay.restpos.async.OnRequestListener;
import com.bitplay.restpos.interfaces.login.ILoginPresenter;
import com.bitplay.restpos.interfaces.login.ILoginView;
import com.bitplay.restpos.models.login.LoginModel;
import com.bitplay.restpos.models.register.RegisterModel;
import com.bitplay.restpos.utils.AppConstants;
import com.bitplay.restpos.utils.NetworkStatus;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.activities.LoginActivity;
import com.bitplay.restpos.views.activities.RegisterActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by vivek on 7/4/2017.
 */

public class RegisterPresenterImpl implements IRegisterPresenter, OnRequestListener {

    private IRegisterView mIRegisterView;
    private RegisterActivity mRegisterActivity;
    private AsyncInteractor mAsyncInteractor;
    private RegisterModel mRegisterModel;

    public RegisterPresenterImpl(IRegisterView mIRegisterView) {
        this.mIRegisterView = mIRegisterView;
        this.mRegisterActivity = (RegisterActivity) mIRegisterView;
        mAsyncInteractor = new AsyncInteractor();
    }

    @Override
    public void registerApiCall(String name, String contactnumber, String email, String password, String address, String fathername, String addharnumber, String pannumber, String selectRole) {

        if (NetworkStatus.checkNetworkStatus(mRegisterActivity)) {
          //  Utils.showProgress(mRegisterActivity);
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", name);
            params.put("contactnumber", contactnumber);
            params.put("email",  email);
            params.put("password", password);
            params.put("address", address);
            params.put("fathername", fathername);
            params.put("addharnumber", addharnumber);
            params.put("pannumber", pannumber);
            params.put("selectRole", selectRole);
            Log.d("params", "" + params.toString());
            mAsyncInteractor.validateCredentialsAsync(this, AppConstants.TAG_ID_REGISTER,
                    AppConstants.URL.REGISTER.getUrl(), params);
        } else {
            Utils.showToast(mRegisterActivity, "Please connect to internet");
        }

    }


    @Override
    public void onRequestCompletion(int pid, JSONObject responseJson, JSONArray responseArray) {

    }

    @Override
    public void onRequestCompletion(int pid, String responseJson) throws JSONException {

        if (pid == AppConstants.TAG_ID_REGISTER) {

            if (responseJson != null) {

                Gson gson = new Gson();

                mRegisterModel= gson.fromJson(responseJson, RegisterModel.class);

                mIRegisterView.onRegisterSuccess(pid, mRegisterModel);
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
            mIRegisterView.onRegisterError(pid, mRegisterModel);
        }
    }

    @Override
    public void onRequestCompletionHomeError(int pid, String error) {

    }



}

