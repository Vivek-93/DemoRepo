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

    /*  private RecyclerView listView;*/
    private SubItemArrayAdapter mSubItemArrayAdapter;

    public FrameLayout mFramelayout;
    private FragmentManager mFragmentManager;
    /*  public TextView mSearch, mEmpty ,mCount;
      public Spinner mSpinner;*/
    public ImageView mBookedItems;

    private Context context;
    private ArrayList<MealDetails> filterList;

    private Button mCetogery, mSearch;

    ArrayList<BookedItems> list = new ArrayList<BookedItems>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_details);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mCetogery = (Button) findViewById(R.id.act_table_details_catogery_btn);
        mSearch = (Button) findViewById(R.id.act_table_details_search_btn);
        mFramelayout = (FrameLayout) findViewById(R.id.act_table_details_framelayout);
    /*    mSearch = (TextView) findViewById(R.id.search_et);
        mSpinner = (Spinner) findViewById(R.id.act_table_spinner);*/
        mBookedItems = (ImageView) findViewById(R.id.act_table_details_toolbar_iv);
       /* mEmpty = (TextView) findViewById(R.id.act_table_details_empty_tv);
        mCount=(TextView)findViewById(R.id.act_table_details_count_toolbar_tv);*/
        context = TableDetailsActivity.this;

        initializeViews();

    }

    private void initializeViews() {

        readFileDataMethod();

        mFragmentManager = getSupportFragmentManager();
        openCatogeryFragment();
   /*
        listView = (RecyclerView) findViewById(R.id.listView);
        listView.setHasFixedSize(true);
        mItemArrayAdapter = new ItemArrayAdapter1(this, mealdetails);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listView.setLayoutManager(mLayoutManager);
        listView.setAdapter(mItemArrayAdapter);*/

        //  searchFunction();

        //   setSearchSpinnerData();
        mBookedItems.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mCetogery.setOnClickListener(this);

    }


 /*   private void setSearchSpinnerData() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cotegory, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);
    }*/


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
/*

    private void searchFunction() {

        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                s = s.toString().toLowerCase();
                ArrayList<MealDetails> filteredList = new ArrayList<>();

                for (int i = 0; i < mealdetails.size(); i++) {

                    final String text = mealdetails.get(i).getItemName().toString().toLowerCase();
                    if (text.contains(s)) {
                        filteredList.add(mealdetails.get(i));
                    }
                }

                listView.setLayoutManager(new LinearLayoutManager(TableDetailsActivity.this));
                mItemArrayAdapter = new ItemArrayAdapter1(context, filteredList);
                listView.setAdapter(mItemArrayAdapter);
                mItemArrayAdapter.notifyDataSetChanged();  // data set changed
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
*/

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.act_table_details_toolbar_iv:
                list = mSubItemArrayAdapter.getArrayList();
                Intent intent = new Intent(TableDetailsActivity.this, BookedItemsActivity.class);
                intent.putExtra("myList", list);
                Log.d("TableDetailsActivity", "list " + list);

                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;

            case R.id.act_table_details_catogery_btn:
                openCatogeryFragment();
                break;

            case R.id.act_table_details_search_btn:
                openSearchFragment();
                break;
        }

    }

    private void openSearchFragment() {

        Bundle bundle = new Bundle();
        bundle.putSerializable("itemList",  mealdetails);
        CaptionSearchFragment csf = new CaptionSearchFragment();
        csf.setArguments(bundle);
        mFragmentManager.beginTransaction().replace(R.id.act_table_details_framelayout,csf).addToBackStack(null).commit();
    }

    private void openCatogeryFragment() {
        Bundle bundle =new Bundle();
        bundle.putSerializable("itemlistCatogery",mealdetails);
        CaptionCatogeryFragment ccf=new CaptionCatogeryFragment();
        ccf.setArguments(bundle);
        mFragmentManager.beginTransaction().replace(R.id.act_table_details_framelayout,ccf).addToBackStack(null).commit();
    }

 /*   @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        switch (selectedItem) {
            case "All items":
                filterList = new ArrayList<>();

                for (int i = 0; i < mealdetails.size(); i++) {

                    filterList.add(mealdetails.get(i));
                }
                setDataInFilterList();

                break;
            case "Veg":
                filterList = new ArrayList<>();

                for (int i = 0; i < mealdetails.size(); i++) {

                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Veg")) {

                        filterList.add(mealdetails.get(i));
                    }
                }
                setDataInFilterList();
                break;

            case "Non Veg.":
                filterList = new ArrayList<>();

                for (int i = 0; i < mealdetails.size(); i++) {

                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Non Veg.")) {

                        filterList.add(mealdetails.get(i));
                    }
                }
                setDataInFilterList();
                break;
            case "Appetizer":
                filterList = new ArrayList<>();

                for (int i = 0; i < mealdetails.size(); i++) {

                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Appetizer")) {

                        filterList.add(mealdetails.get(i));
                    }
                }
                setDataInFilterList();
                break;
            case "Beverages":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {

                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Beverages")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Breakfast":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Breakfast")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Burger":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Burger")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Chinese Main Course":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Chinese Main Course")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;


            case "Chinese Starter":
                filterList = new ArrayList<>();

                for (int i = 0; i < mealdetails.size(); i++) {

                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Chinese Starter")) {

                        filterList.add(mealdetails.get(i));
                    }
                }
                setDataInFilterList();
                break;
            case "Chinese Soups":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {

                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Chinese Soups")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Main Course Lamb":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Main Course Lamb")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Kitchen Beverages":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Kitchen Beverages")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Frecnch Roll Sandwich":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Frecnch Roll Sandwich")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;


            case "Main Course Chicken":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Main Course Chicken")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Main Course Fish":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Main Course Fish")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Main Course Prawn":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Main Course Prawn")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;


            case "Desserts":
                filterList = new ArrayList<>();

                for (int i = 0; i < mealdetails.size(); i++) {

                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Desserts")) {

                        filterList.add(mealdetails.get(i));
                    }
                }
                setDataInFilterList();
                break;
            case "Main Course Extra":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {

                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Main Course Extra")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Extra Starter":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Extra Starter")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Rice Noodles":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Rice Noodles")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Salad":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Salad")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;

            case "Sizzlers":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Sizzlers")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;


            case "Soup":
                filterList = new ArrayList<>();

                for (int i = 0; i < mealdetails.size(); i++) {

                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Soup")) {

                        filterList.add(mealdetails.get(i));
                    }
                }
                setDataInFilterList();
                break;
            case "Main Course Salad":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {

                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Main Course Salad")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Sandwich":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Sandwich")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "Momos":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("Momos")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;
            case "PIZZA":
                filterList = new ArrayList<>();
                for (int i = 0; i < mealdetails.size(); i++) {
                    String burger = mealdetails.get(i).getCategory().toString();
                    if (burger.contains("PIZZA")) {

                        filterList.add(mealdetails.get(i));
                    }
                }

                setDataInFilterList();
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setDataInFilterList() {

        listView.setLayoutManager(new LinearLayoutManager(TableDetailsActivity.this));
        mItemArrayAdapter = new ItemArrayAdapter1(context, filterList);
        listView.setAdapter(mItemArrayAdapter);
        mItemArrayAdapter.notifyDataSetChanged();
    }*/


    @Override
    public void onResume() {
        super.onResume();


    }
}