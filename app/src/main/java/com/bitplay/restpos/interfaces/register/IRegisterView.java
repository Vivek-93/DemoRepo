package com.bitplay.restpos.interfaces.register;

import com.bitplay.restpos.models.register.RegisterModel;

/**
 * Created by Vivek on 14-12-2017.
 */

public interface IRegisterView {

    void onRegisterSuccess(int pid, RegisterModel registerModel);

    void onRegisterError(int pid, RegisterModel registerErrorModel);
}
