package com.bitplay.restpos.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitplay.restpos.R;
import com.bitplay.restpos.adapters.CaptionRecyclerViewAdaptor;
import com.bitplay.restpos.database.DatabaseHelper;
import com.bitplay.restpos.models.login.LoginModel;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout hrHomeActDrawerLayout;
    public ImageView mHamBurgerIconIV;
    public NavigationView mLeftNavView;
    public RecyclerView captionRV;
    public TextView mUserName, mUserRole, mLogout;
    private CaptionRecyclerViewAdaptor captionRecyclerViewAdaptor;

    String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private DatabaseHelper helper;
    private String name, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        name = getIntent().getStringExtra("userName");
        role = getIntent().getStringExtra("userRole");
        Log.d("mainact", "" + name + role);
        captionRV = (RecyclerView) findViewById(R.id.act_home_caption_rv);
        mHamBurgerIconIV = (ImageView) findViewById(R.id.mainActHamBurgerIconIV);


        initilizeView();
    }

    private void initilizeView() {


        settingNavigtionView();

        settingClickListner();
        int numberOfColumns = 2;
        captionRV.setHasFixedSize(true);
        captionRV.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        captionRecyclerViewAdaptor = new CaptionRecyclerViewAdaptor(this, data);
        captionRV.setAdapter(captionRecyclerViewAdaptor);
    }

    private void settingNavigtionView() {

        hrHomeActDrawerLayout = (DrawerLayout) findViewById(R.id.main_act_drawer_layout);
        mLeftNavView = (NavigationView) findViewById(R.id.mainActLeftNavView);
        View header = mLeftNavView.getHeaderView(0);
        mUserName = (TextView) header.findViewById(R.id.act_main_username_tv);
        mUserRole = (TextView) header.findViewById(R.id.act_main_userrole_tv);
        mLogout = (TextView) findViewById(R.id.act_home_main_logout_tv);

        mUserName.setText(name);
        mUserRole.setText(role);

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }

    private void settingClickListner() {
        mHamBurgerIconIV.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainActHamBurgerIconIV:
                hrHomeActDrawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

