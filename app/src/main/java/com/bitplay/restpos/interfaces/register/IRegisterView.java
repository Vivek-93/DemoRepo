package com.bitplay.restpos.interfaces.register;

/**
 * Created by Vivek on 14-12-2017.
 */

public interface IRegisterView {

    void onRegisterSuccess(int pid, String registerModel);

    void onRegisterError(int pid, String registerErrorModel);
}
