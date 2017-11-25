package com.bitplay.restpos.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bitplay.restpos.database.DatabaseHelper;
import com.bitplay.restpos.extra.User;
import com.bitplay.restpos.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHelper databaseHelper;
    public EditText mEmailET, mPasswordET;
    public Button mLoginButton;
    public TextView mForgotTv, mRegisterTv;
    public User user;


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

        databaseHelper = new DatabaseHelper(this);
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


        if (mEmailET.getText().toString().length() == 0 && mPasswordET.getText().toString().length() == 0) {

            Toast.makeText(this, "Enter email and password", Toast.LENGTH_LONG).show();
        } else if (databaseHelper.checkUser(mEmailET.getText().toString().trim(), mPasswordET.getText().toString().trim(), "Captain")) {
            Intent accountsIntent = new Intent(LoginActivity.this, MainActivity.class);
            accountsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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
        }
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
}
