package com.bitplay.restpos.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bitplay.restpos.adapters.ItemArrayAdapter1;
import com.bitplay.restpos.adapters.SubItemArrayAdapter;
import com.bitplay.restpos.extra.BookedItems;
import com.bitplay.restpos.extra.MealDetails;
import com.bitplay.restpos.R;
import com.bitplay.restpos.interfaces.gettableguestdetail.GetGuestDetailPresenterImpl;
import com.bitplay.restpos.interfaces.gettableguestdetail.IGetGuestDetailPresenter;
import com.bitplay.restpos.interfaces.gettableguestdetail.IGetGuestDetailView;
import com.bitplay.restpos.models.getguestdetail.GetGuestDetailModel;
import com.bitplay.restpos.utils.Sharedpreferences;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.fragments.caption.CaptionCatogeryFragment;
import com.bitplay.restpos.views.fragments.caption.CaptionSearchFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class TableDetailsActivity extends AppCompatActivity implements View.OnClickListener,IGetGuestDetailView {

    private SubItemArrayAdapter mSubItemArrayAdapter;

    public FrameLayout mFramelayout;
    private FragmentManager mFragmentManager;
    public ImageView mBookedItems, mBackIv;

    private Context context;
    public TextView mGuestName, mGuestPhone, mGuestTable, mKotNumber;

    private Button mCetogery, mSearch;

    private IGetGuestDetailPresenter mIGetGuestDetailPresenter;
    public Sharedpreferences mPref = Sharedpreferences.getUserDataObj(TableDetailsActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mCetogery = (Button) findViewById(R.id.act_table_details_catogery_btn);
        mSearch = (Button) findViewById(R.id.act_table_details_search_btn);
        mFramelayout = (FrameLayout) findViewById(R.id.act_table_details_framelayout);
        mGuestName = (TextView) findViewById(R.id.guest_name_tv);
        mGuestPhone = (TextView) findViewById(R.id.guest_mobile_number_tv);
        mGuestTable = (TextView) findViewById(R.id.guest_table_number_tv);
        mKotNumber = (TextView) findViewById(R.id.guest_kot_number_tv);
        mBackIv = (ImageView) findViewById(R.id.act_table_details_back_iv);

        mBookedItems = (ImageView) findViewById(R.id.act_table_details_toolbar_iv);

        context = TableDetailsActivity.this;

        initializeViews();

    }

    private void initializeViews() {

        mFragmentManager = getSupportFragmentManager();

        openCatogeryFragment();

        mBookedItems.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mCetogery.setOnClickListener(this);
        mBackIv.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.act_table_details_toolbar_iv:

                Intent intent = new Intent(TableDetailsActivity.this, BookedItemsActivity.class);
                startActivity(intent);
                break;

            case R.id.act_table_details_catogery_btn:
                openCatogeryFragment();
                break;

            case R.id.act_table_details_search_btn:
                openSearchFragment();
                break;

            case R.id.act_table_details_back_iv:
                Intent intent1=new Intent(this,MainActivity.class);
                startActivity(intent1);
                break;
        }

    }

    private void openSearchFragment() {

      //  Bundle bundle = new Bundle();
      //  bundle.putSerializable("itemList", mealdetails);
        CaptionSearchFragment csf = new CaptionSearchFragment();
     //   csf.setArguments(bundle);
        mFragmentManager.beginTransaction().replace(R.id.act_table_details_framelayout, csf).addToBackStack(null).commit();
    }

    private void openCatogeryFragment() {

      /*  Bundle bundle = new Bundle();
        bundle.putString("tableno", mGuestTable.getText().toString());*/
        CaptionCatogeryFragment ccf = new CaptionCatogeryFragment();
     //   ccf.setArguments(bundle);
        mFragmentManager.beginTransaction().replace(R.id.act_table_details_framelayout, ccf).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {

        Intent intent1=new Intent(this,MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);
        super.onBackPressed();
    }


    @Override
    public void onGetGuestDetailSuccess(int pid, GetGuestDetailModel getGuestDetailModel) {

        Utils.stopProgress(TableDetailsActivity.this);


        if (getGuestDetailModel!=null) {

            mGuestName.setText("" + getGuestDetailModel.getGuestname());
            mGuestPhone.setText("" + getGuestDetailModel.getPhonenumber());
            mGuestTable.setText("Table" + getGuestDetailModel.getTablenumber());
          //  mPref.setTableNumber(mGuestTable.getText().toString());
        }
    }

    @Override
    public void onGetGuestDetailError(int pid, String getGuestDetailError) {

        Utils.stopProgress(TableDetailsActivity.this);
        Toast.makeText(this, "Some error", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        mIGetGuestDetailPresenter = new GetGuestDetailPresenterImpl(TableDetailsActivity.this);
        mIGetGuestDetailPresenter.getGuestDetailApiCall(Integer.parseInt(mPref.getTableId()));

        Log.d("TableDetailActivity","comming"+mPref.getTableId());
    }
}