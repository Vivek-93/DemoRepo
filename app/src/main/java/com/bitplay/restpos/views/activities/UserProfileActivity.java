package com.bitplay.restpos.views.activities;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitplay.restpos.R;
import com.bitplay.restpos.interfaces.login.LoginPresenterImpl;
import com.bitplay.restpos.interfaces.profiledetails.IProfileDetailsPresenter;
import com.bitplay.restpos.interfaces.profiledetails.IProfileDetailsView;
import com.bitplay.restpos.interfaces.profiledetails.ProfileDetailsPresenterImpl;
import com.bitplay.restpos.models.profiledetails.ProfileModel;
import com.bitplay.restpos.utils.Sharedpreferences;
import com.bitplay.restpos.utils.Utils;

public class UserProfileActivity extends AppCompatActivity implements IProfileDetailsView, View.OnClickListener {

    public TextView profileMobileNumber, profileUserName, profileEmail, profileAddress, profileFathers, profileAadharno, profilePan, profileRole;
    public ImageView mBackIv ,mEditImage,mEditDoneIv;
    private Sharedpreferences mPref = Sharedpreferences.getUserDataObj(UserProfileActivity.this);
    private IProfileDetailsPresenter mIProfileDetailsPresenter;
    public String Tag ="UserProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        profileUserName = (TextView) findViewById(R.id.act_user_profile_name_tv);
        profileMobileNumber = (TextView) findViewById(R.id.act_user_profile_mobileno_tv);
        profileEmail = (TextView) findViewById(R.id.act_user_profile_email_tv);
        profileAddress = (TextView) findViewById(R.id.act_user_profile_address_tv);
        profileFathers = (TextView) findViewById(R.id.act_user_profile_father_tv);
        profileAadharno = (TextView) findViewById(R.id.act_user_profile_aadhar_tv);
        profilePan = (TextView) findViewById(R.id.act_user_profile_pan_tv);
        profileRole = (TextView) findViewById(R.id.act_user_profile_role_tv);
        mBackIv = (ImageView) findViewById(R.id.act_user_profile_back_iv);
        mEditImage=(ImageView)findViewById(R.id.act_user_profile_edit_iv);
        mEditDoneIv=(ImageView)findViewById(R.id.act_user_profile_edit_done_iv);
        mIProfileDetailsPresenter = new ProfileDetailsPresenterImpl(UserProfileActivity.this);
        initializeViews();

    }


    private void initializeViews() {

        mBackIv.setOnClickListener(this);
        mEditImage.setOnClickListener(this);
        try {
            mIProfileDetailsPresenter.profileDetailApiCall(Integer.parseInt(mPref.getUserId()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onProfileDetailSuccess(int pid, ProfileModel profileModel) {
        Utils.stopProgress(UserProfileActivity.this);
        profileUserName.setText(profileModel.getName().toString());
        profileMobileNumber.setText(profileModel.getContactnumber().toString());
        profileEmail.setText(profileModel.getEmail().toString());
        profileAddress.setText(profileModel.getAddress().toString());
        profileFathers.setText(profileModel.getFathername().toString());
        profileAadharno.setText(profileModel.getAddharnumber().toString());
        profilePan.setText(profileModel.getPannumber().toString());
        profileRole.setText(profileModel.getSelectRole().toString());

    }

    @Override
    public void onProfileDetailError(int pid, String profileErrorModel) {
        Utils.stopProgress(UserProfileActivity.this);
        Toast.makeText(this, ""+profileErrorModel, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mIProfileDetailsPresenter = new ProfileDetailsPresenterImpl(UserProfileActivity.this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.act_user_profile_back_iv:
                Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

            case R.id.act_user_profile_edit_iv:
                userProfileEditFun();
                break;


        }

    }

    private void userProfileEditFun() {
        Intent intent=new Intent(UserProfileActivity.this,UserProfileUpdateActivity.class);
        intent.putExtra("name",profileUserName.getText().toString());
        intent.putExtra("mobilenumber",profileMobileNumber.getText().toString());
        intent.putExtra("email",profileEmail.getText().toString());
        intent.putExtra("address",profileAddress.getText().toString());
        intent.putExtra("father",profileFathers.getText().toString());
        intent.putExtra("aadhar",profileAadharno.getText().toString());
        intent.putExtra("pan",profilePan.getText().toString());
        intent.putExtra("role",profileRole.getText().toString());
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

        Intent intent= new Intent(UserProfileActivity.this,MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
