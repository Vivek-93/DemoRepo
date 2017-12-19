package com.bitplay.restpos.views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitplay.restpos.R;
import com.bitplay.restpos.adapters.CaptionRecyclerViewAdaptor;
import com.bitplay.restpos.database.DatabaseHelper;
import com.bitplay.restpos.interfaces.tabledetails.ITableDetailPresenter;
import com.bitplay.restpos.interfaces.tabledetails.ITableDetailView;
import com.bitplay.restpos.interfaces.tabledetails.TableDetailPresenterImpl;
import com.bitplay.restpos.models.login.LoginModel;
import com.bitplay.restpos.models.tabledetails.TableDetailModel;
import com.bitplay.restpos.utils.Sharedpreferences;
import com.bitplay.restpos.utils.Utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, ITableDetailView {

    private DrawerLayout hrHomeActDrawerLayout;
    public ImageView mHamBurgerIconIV;
    public NavigationView mLeftNavView;
    public RecyclerView captionRV;
    public TextView mUserName, mUserRole, mLogout;
    private CaptionRecyclerViewAdaptor captionRecyclerViewAdaptor;
    private List<TableDetailModel> data;
    private CardView mHeaderCv;
    public Sharedpreferences mPref = Sharedpreferences.getUserDataObj(MainActivity.this);
    private ITableDetailPresenter mITableDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        captionRV = (RecyclerView) findViewById(R.id.act_home_caption_rv);
        mHamBurgerIconIV = (ImageView) findViewById(R.id.mainActHamBurgerIconIV);

        initilizeView();
    }

    private void initilizeView() {

        data = new ArrayList<>();
        settingNavigtionView();
        settingClickListner();


    }


    private void settingNavigtionView() {

        hrHomeActDrawerLayout = (DrawerLayout) findViewById(R.id.main_act_drawer_layout);
        mLeftNavView = (NavigationView) findViewById(R.id.mainActLeftNavView);
        View header = mLeftNavView.getHeaderView(0);
        mHeaderCv = (CardView) header.findViewById(R.id.drawer_header_cv);
        mUserName = (TextView) header.findViewById(R.id.act_main_username_tv);
        mUserRole = (TextView) header.findViewById(R.id.act_main_userrole_tv);
        mLogout = (TextView) findViewById(R.id.act_home_main_logout_tv);

        mUserName.setText(mPref.getUsername().toString());
        mUserRole.setText(mPref.getUserRole().toString());

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }

    private void settingClickListner() {
        mHamBurgerIconIV.setOnClickListener(this);
        mHeaderCv.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainActHamBurgerIconIV:
                hrHomeActDrawerLayout.openDrawer(Gravity.LEFT);
                break;

            case R.id.drawer_header_cv:
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                hrHomeActDrawerLayout.closeDrawer(Gravity.LEFT);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mITableDetailPresenter = new TableDetailPresenterImpl(MainActivity.this);
        mITableDetailPresenter.tableDetailsApiCall(Integer.parseInt(mPref.getUserId()));
    }

    @Override
    public void onTableDetailsSuccess(int pid, TableDetailModel[] tableDetailModel) {


        Log.d("MainActivity", "table size" + tableDetailModel.length);

        data = Arrays.asList(tableDetailModel);
        Utils.stopProgress(MainActivity.this);
        if (tableDetailModel != null) {

            int numberOfColumns = 2;
            captionRV.setHasFixedSize(true);
            captionRV.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
            captionRecyclerViewAdaptor = new CaptionRecyclerViewAdaptor(this,data);
            captionRV.setAdapter(captionRecyclerViewAdaptor);

        }

        Log.d("MainActivity", "list size" + data.size());


    }

    @Override
    public void onTableDetailsError(int pid, TableDetailModel[] tableDetailErrorModel) {

    }
}

