package com.bitplay.restpos.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bitplay.restpos.database.DatabaseHelper;
import com.bitplay.restpos.extra.User;
import com.bitplay.restpos.R;
import com.bitplay.restpos.interfaces.login.ILoginPresenter;
import com.bitplay.restpos.interfaces.login.ILoginView;
import com.bitplay.restpos.interfaces.login.LoginPresenterImpl;
import com.bitplay.restpos.models.login.LoginModel;
import com.bitplay.restpos.utils.Sharedpreferences;
import com.bitplay.restpos.utils.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {


    public EditText mEmailET, mPasswordET;
    public Button mLoginButton;
    public TextView mForgotTv, mRegisterTv;

    // LoginPresenter Instance
    private ILoginPresenter mILoginPresenter;

    // Shared Preferences
    private Sharedpreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mEmailET = (EditText) findViewById(R.id.login_act_email_et);
        mPasswordET = (EditText) findViewById(R.id.login_act_password_et);
        mLoginButton = (Button) findViewById(R.id.login_act_login_button);
        mForgotTv = (TextView) findViewById(R.id.login_act_forgot_password_tv);
        mRegisterTv = (TextView) findViewById(R.id.login_act_sign_up_tv);
        mRegisterTv.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);

        initilizeView();

    }

    private void initilizeView() {

        mPrefs = Sharedpreferences.getUserDataObj(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.login_act_login_button:
                login_func();
                break;

            case R.id.login_act_sign_up_tv:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void login_func() {
        getAllDataValues();

        mILoginPresenter.loginApiCall(mEmailET.getText().toString(), mPasswordET.getText().toString());

       /* if (mEmailET.getText().toString().length() == 0 && mPasswordET.getText().toString().length() == 0) {

            Toast.makeText(this, "Enter email and password", Toast.LENGTH_LONG).show();
        } else if (mLo.checkUser(mEmailET.getText().toString().trim(), mPasswordET.getText().toString().trim(), "Captain")) {
            Intent accountsIntent = new Intent(LoginActivity.this, MainActivity.class);
            accountsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            accountsIntent.putExtra("userName",mEmailET.getText().toString());
            accountsIntent.putExtra("userRole","Captain");
            emptyInputEditText();
            startActivity(accountsIntent);
        } else if (databaseHelper.checkUser(mEmailET.getText().toString().trim(), mPasswordET.getText().toString().trim(), "Cashier")) {
            Intent accountsIntent = new Intent(LoginActivity.this, CashierMainActivity.class);
            accountsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            emptyInputEditText();
            startActivity(accountsIntent);
        } else if (databaseHelper.checkUser(mEmailET.getText().toString().trim(), mPasswordET.getText().toString().trim(), "Kitchen Display")) {
            Intent accountsIntent = new Intent(LoginActivity.this, KDMainActivity.class);
            accountsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            emptyInputEditText();
            startActivity(accountsIntent);
        } else {
            // Snack Bar to show success message that record is wrong
            Toast.makeText(this, "Incorrect email and password", Toast.LENGTH_LONG).show();
        }*/
    }

    private void getAllDataValues() {

        if (mEmailET.getText().toString().length() == 0) {
            Toast.makeText(this, "Name can't be blank", Toast.LENGTH_SHORT).show();
            mEmailET.setError("Name can't be blank");
            return;
        } else if (mPasswordET.getText().toString().length() == 0) {
            Toast.makeText(this, "Password can't be blank", Toast.LENGTH_SHORT).show();
            mPasswordET.setError("Password can't be blank");
            return;
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        mEmailET.setText(null);
        mPasswordET.setText(null);
    }

    private void forgotPasswordFunc() {
        Toast.makeText(this, "Forgot password", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginSuccess(int pid, LoginModel loginModel) {
        Utils.stopProgress(LoginActivity.this);
        if (loginModel.getSelectRole().toString().equalsIgnoreCase("Captain")) {
            Log.d("LoginActivity", "" + loginModel.getSelectRole().toString());
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            mPrefs.setUsername(loginModel.getName().toString());
            mPrefs.setUserRole(loginModel.getSelectRole().toString());
            mPrefs.setUserId(loginModel.getId().toString());
         //   mPrefs.setUserId(loginModel.getId().toString());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (loginModel.getSelectRole().toString().equalsIgnoreCase("cashier")) {
            Intent intent = new Intent(LoginActivity.this, CashierMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        Log.d("LoginActivity", "success" + loginModel.getName().toString());

        // Utils.stopProgress(LoginActivity.this);

    }


    @Override
    public void onLoginError(int pid, LoginModel loginErrorModel) {

        Utils.stopProgress(LoginActivity.this);
        Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        mILoginPresenter = new LoginPresenterImpl(LoginActivity.this);
    }
}
