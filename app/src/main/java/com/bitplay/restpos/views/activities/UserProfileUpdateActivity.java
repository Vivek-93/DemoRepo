package com.bitplay.restpos.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitplay.restpos.R;
import com.bitplay.restpos.interfaces.profileupdate.IProfileUpdatePresenter;
import com.bitplay.restpos.interfaces.profileupdate.IProfileUpdateView;
import com.bitplay.restpos.interfaces.profileupdate.ProfileUpdatePresenterImpl;
import com.bitplay.restpos.utils.Sharedpreferences;
import com.bitplay.restpos.utils.Utils;

public class UserProfileUpdateActivity extends AppCompatActivity implements View.OnClickListener, IProfileUpdateView {

    public EditText mUpdateName, mUpdateNumber, mUpdateEmail, mUpdateAdd, mUpdateFather, mUpdateAadhar, mUpdatePan;
    public Button mUpdateDone;
    public TextView mUpdateRole;

    private IProfileUpdatePresenter mIProfileUpdatePresenter;
    public String updateName, updateEmail, updateRole, updateAdd, updateFather, updatePan;
    public int updateNumber, updateAadhar;
    private Sharedpreferences mPref = Sharedpreferences.getUserDataObj(UserProfileUpdateActivity.this);
    private String name, number, email, address, father, aadhar, pan, profileRole;
    public ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_update);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mUpdateName = (EditText) findViewById(R.id.act_user_profile_update_name_et);
        mUpdateNumber = (EditText) findViewById(R.id.act_user_profile_update_mobileno_et);
        mUpdateEmail = (EditText) findViewById(R.id.act_user_profile_update_email_et);
        mUpdateRole = (TextView) findViewById(R.id.act_user_profile_update_role_et);
        mUpdateAdd = (EditText) findViewById(R.id.act_user_profile_update_address_tv);
        mUpdateFather = (EditText) findViewById(R.id.act_user_profile_update_father_et);
        mUpdateAadhar = (EditText) findViewById(R.id.act_user_profile_update_aadhar_et);
        mUpdatePan = (EditText) findViewById(R.id.act_user_profile_update_pan_et);
        mUpdateDone = (Button) findViewById(R.id.act_user_profile_edit_done_btn);
        mBack = (ImageView) findViewById(R.id.act_user_profile_update_back_iv);

        mIProfileUpdatePresenter = new ProfileUpdatePresenterImpl(UserProfileUpdateActivity.this);

        initializeViews();

    }

    private void initializeViews() {
        mBack.setOnClickListener(this);
        gettingPreviousDetails();


    }

    private void gettingDetails() {
        updateName = mUpdateName.getText().toString();
        updateNumber = Integer.parseInt(mUpdateNumber.getText().toString());
        updateEmail = mUpdateEmail.getText().toString();
        updateRole = mUpdateRole.getText().toString();
        updateAdd = mUpdateAdd.getText().toString();
        updateFather = mUpdateFather.getText().toString();
        updateAadhar = Integer.parseInt(mUpdateAadhar.getText().toString());
        updatePan = mUpdatePan.getText().toString();
    }

    private void gettingPreviousDetails() {

        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("mobilenumber");
        email = getIntent().getStringExtra("email");
        address = getIntent().getStringExtra("address");
        father = getIntent().getStringExtra("father");
        aadhar = getIntent().getStringExtra("aadhar");
        pan = getIntent().getStringExtra("pan");
        profileRole = getIntent().getStringExtra("role");

        mUpdateName.setText(name);
        mUpdateNumber.setText(number);
        mUpdateEmail.setText(email);
        mUpdateRole.setText(profileRole);
        mUpdateAdd.setText(address);
        mUpdateFather.setText(father);
        mUpdateAadhar.setText(aadhar);
        mUpdatePan.setText(pan);
        mUpdateDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.act_user_profile_edit_done_btn:
                updateFuunction();
                break;
            case R.id.act_user_profile_update_back_iv:
                onBackPressed();
                break;
        }

    }

    private void updateFuunction() {
        gettingDetails();
        mIProfileUpdatePresenter.profileUpdateApiCall(Integer.parseInt(mPref.getUserId()), updateName, updateNumber, updateEmail, updateAdd, updateFather, updateAadhar, updatePan, updateRole);
        mPref.setUsername(updateName);
    }

    @Override
    public void onProfileUpdateSuccess(int pid, String profileUpdateModel) {
        Utils.stopProgress(UserProfileUpdateActivity.this);
        Intent intent = new Intent(UserProfileUpdateActivity.this, UserProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
      //  Toast.makeText(this, "" + profileUpdateModel.replaceAll("\"", ""), Toast.LENGTH_SHORT).show();
         startActivity(intent);

    }

    @Override
    public void onProfileUpdateError(int pid, String profileUpdateErrorModel) {

        Utils.stopProgress(UserProfileUpdateActivity.this);
        Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        gettingDetails();
        mIProfileUpdatePresenter = new ProfileUpdatePresenterImpl(UserProfileUpdateActivity.this);
    }

}
