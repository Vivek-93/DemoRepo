package com.bitplay.restpos.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bitplay.restpos.R;
import com.bitplay.restpos.adapters.KDMainAdapter;
import com.bitplay.restpos.adapters.KdHomeViewPagerAdapter;

public class KDMainActivity extends AppCompatActivity {

    // FragmentManager Instance
    private FragmentManager mFragmentManager;

    //KDHomeViewPager Adapter
    private KdHomeViewPagerAdapter mKdHomeViewPagerAdapter ;

    public TabLayout mKdTablayout;
    public ViewPager mKdViewPager;
    public RecyclerView mKdMainRv;

    private KDMainAdapter mKDMainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kdmain);
        mKdTablayout=(TabLayout)findViewById(R.id.act_kd_main_table_layout);
        mKdViewPager=(ViewPager)findViewById(R.id.act_kd_main_viewpager);
        mKdMainRv=(RecyclerView)findViewById(R.id.act_kd_main_rv);

        initializeViews();

    }

    private void initializeViews() {

        mFragmentManager = getSupportFragmentManager();

        settingUpTabLayout();
        settingUpViewPager();
        settingUpMainRecyclerView();

    }

    private void settingUpMainRecyclerView() {

        mKdMainRv.setHasFixedSize(true);
        mKDMainAdapter = new KDMainAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mKdMainRv.setLayoutManager(mLayoutManager);
        mKdMainRv.setAdapter(mKDMainAdapter);

    }

    private void settingUpViewPager() {

        mKdHomeViewPagerAdapter = new KdHomeViewPagerAdapter(mFragmentManager, mKdTablayout.getTabCount());
        mKdViewPager.setAdapter(mKdHomeViewPagerAdapter);
        mKdViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mKdTablayout));
        mKdViewPager.setOffscreenPageLimit(0);

    }

    private void settingUpTabLayout() {
        mKdTablayout.addTab(mKdTablayout.newTab().setText("New Order"));
        mKdTablayout.addTab(mKdTablayout.newTab().setText("Ready"));
        mKdTablayout.addTab(mKdTablayout.newTab().setText("Completed"));
        mKdTablayout.addTab(mKdTablayout.newTab().setText("Reports & Setting"));
        mKdTablayout.addTab(mKdTablayout.newTab().setText("Comolidated report"));
        mKdTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mKdViewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                mKdViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

}
