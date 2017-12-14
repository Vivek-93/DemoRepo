package com.bitplay.restpos.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bitplay.restpos.R;
import com.bitplay.restpos.interfaces.profiledetails.IProfileDetailsPresenter;
import com.bitplay.restpos.interfaces.profiledetails.IProfileDetailsView;
import com.bitplay.restpos.models.profiledetails.ProfileModel;
import com.bitplay.restpos.utils.Utils;

public class UserProfileActivity extends AppCompatActivity implements IProfileDetailsView {

    public TextView profileMobileNumber, profileUserName, profileEmail, profileAddress, profileFathers, profileAadharno, profilePan , profileRole;

    private IProfileDetailsPresenter mIProfileDetailsPresenter;

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

        initializeViews();

    }

    private void initializeViews() {

        mIProfileDetailsPresenter.profileDetailApiCall(63);

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
    public void onProfileDetailError(int pid, ProfileModel profileErrorModel) {
        Utils.stopProgress(UserProfileActivity.this);
        Toast.makeText(this, "User Profile error", Toast.LENGTH_SHORT).show();

    }
}
