package com.bitplay.restpos.interfaces.login;

import com.bitplay.restpos.models.login.LoginModel;

/**
 * Created by Vivek on 26-04-2017.
 */

public interface ILoginView {

    void onLoginSuccess(int pid, LoginModel loginModel);

    void onLoginError(int pid, String loginErrorModel);


}
