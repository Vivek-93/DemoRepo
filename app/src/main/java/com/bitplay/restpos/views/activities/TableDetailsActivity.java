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

import com.bitplay.restpos.adapters.ItemArrayAdapter1;
import com.bitplay.restpos.adapters.SubItemArrayAdapter;
import com.bitplay.restpos.extra.BookedItems;
import com.bitplay.restpos.extra.MealDetails;
import com.bitplay.restpos.R;
import com.bitplay.restpos.views.fragments.caption.CaptionCatogeryFragment;
import com.bitplay.restpos.views.fragments.caption.CaptionSearchFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class TableDetailsActivity extends AppCompatActivity implements View.OnClickListener/*, AdapterView.OnItemSelectedListener */ {

    private SubItemArrayAdapter mSubItemArrayAdapter;

    public FrameLayout mFramelayout;
    private FragmentManager mFragmentManager;
    public ImageView mBookedItems, mBackIv;

    private Context context;
    private ArrayList<MealDetails> filterList;
    public TextView mGuestName, mGuestPhone, mGuestTable, mKotNumber;

    private Button mCetogery, mSearch;

    ArrayList<BookedItems> list = new ArrayList<BookedItems>();
    private String guestName, guestPhone, guestTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        guestName = getIntent().getStringExtra("guestName");
        guestPhone = getIntent().getStringExtra("guestPhone");
        guestTable = getIntent().getStringExtra("guestTable");
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

        readFileDataMethod();
        mGuestName.setText("" + guestName);
        mGuestPhone.setText("" + guestPhone);
        mGuestTable.setText("" + guestTable);

        mFragmentManager = getSupportFragmentManager();
        openCatogeryFragment();

        mBookedItems.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mCetogery.setOnClickListener(this);
        mBackIv.setOnClickListener(this);

    }


    ArrayList<MealDetails> mealdetails = new ArrayList<>();

    private void readFileDataMethod() {
        InputStream inputStream = getResources().openRawResource(R.raw.restaurant_menu_v);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

        String line = "";

        try {
            while ((line = reader.readLine()) != null) {

                String[] tockons = line.split(",");
                MealDetails sample = new MealDetails();
                sample.setItemName(tockons[0]);
                sample.setShortCode(tockons[1]);
                sample.setOnlineDisplayName(tockons[2]);
                sample.setCategory(tockons[3]);
                sample.setPrice(tockons[4]);
                sample.setMinimumPreparationTime(tockons[5]);
                sample.sethSNCode(tockons[6]);
                sample.setDescription(tockons[7]);
                sample.setAvailable(tockons[8]);
                mealdetails.add(sample);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.act_table_details_toolbar_iv:
                //   list = mSubItemArrayAdapter.getArrayList();
                Intent intent = new Intent(TableDetailsActivity.this, BookedItemsActivity.class);
              /*  intent.putExtra("myList", list);
                Log.d("TableDetailsActivity", "list " + list);

                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);*/
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

        Bundle bundle = new Bundle();
        bundle.putSerializable("itemList", mealdetails);
        CaptionSearchFragment csf = new CaptionSearchFragment();
        csf.setArguments(bundle);
        mFragmentManager.beginTransaction().replace(R.id.act_table_details_framelayout, csf).addToBackStack(null).commit();
    }

    private void openCatogeryFragment() {

        Bundle bundle = new Bundle();
        bundle.putSerializable("itemlistCatogery", mealdetails);
        CaptionCatogeryFragment ccf = new CaptionCatogeryFragment();
        ccf.setArguments(bundle);
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
    public void onResume() {
        super.onResume();

    }
}