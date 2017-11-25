package com.bitplay.restpos.views.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitplay.restpos.R;
import com.bitplay.restpos.adapters.CaptionRecyclerViewAdaptor;
import com.bitplay.restpos.database.DatabaseHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout hrHomeActDrawerLayout;
    public ImageView mHamBurgerIconIV;
    public NavigationView mLeftNavView;
    public RecyclerView captionRV;
    public TextView mUserName;
    private CaptionRecyclerViewAdaptor captionRecyclerViewAdaptor;

    String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        captionRV = (RecyclerView) findViewById(R.id.act_home_caption_rv);
        mHamBurgerIconIV = (ImageView) findViewById(R.id.mainActHamBurgerIconIV);
        mUserName=(TextView)findViewById(R.id.act_main_username_tv);

        initilizeView();
    }

    private void initilizeView() {

        settingLeftNavigtionView();
        settingClickListner();
        int numberOfColumns = 2;
        captionRV.setHasFixedSize(true);
        captionRV.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        captionRecyclerViewAdaptor = new CaptionRecyclerViewAdaptor(this, data);
        captionRV.setAdapter(captionRecyclerViewAdaptor);

    }

    private void settingClickListner() {
        mHamBurgerIconIV.setOnClickListener(this);
    }

    private void settingLeftNavigtionView() {
        hrHomeActDrawerLayout = (DrawerLayout) findViewById(R.id.main_act_drawer_layout);
        mLeftNavView = (NavigationView) findViewById(R.id.mainActLeftNavView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainActHamBurgerIconIV:
                hrHomeActDrawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }
}

