package com.bitplay.restpos.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitplay.restpos.adapters.BookedItemsAdapter;
import com.bitplay.restpos.extra.BookedItems;
import com.bitplay.restpos.R;
import com.bitplay.restpos.extra.UpdateItems;

import java.util.ArrayList;
import java.util.List;

public class BookedItemsActivity extends AppCompatActivity implements View.OnClickListener {

    public RecyclerView mBookedRv;
    public LinearLayout mEmptyLl, mLl;
    private BookedItemsAdapter mBookedItemsAdapter;
    public TextView mTotalBillPrice;
    public ImageButton mRefresh;

    //  List<BookedItems> itemslist;
    private ArrayList<BookedItems> myList = new ArrayList<>();
    private ArrayList<UpdateItems> updateList=new ArrayList<>();
    private int total;
    private int tota;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_items);
        mBookedRv = (RecyclerView) findViewById(R.id.act_booked_table_items_rv);
        mEmptyLl = (LinearLayout) findViewById(R.id.act_booked_item_empty_ll);
        mLl = (LinearLayout) findViewById(R.id.act_booked_item_ll);
        mTotalBillPrice = (TextView) findViewById(R.id.act_booked_items_totalprice_tv);
        mRefresh=(ImageButton)findViewById(R.id.act_booked_items_refresh_ib);
        myList = (ArrayList<BookedItems>) getIntent().getSerializableExtra("myList");

        initilizeView();
    }

    private void initilizeView() {

        mRefresh.setOnClickListener(this);

        for (int i = 0; i < myList.size(); i++) {
            int quantity = Integer.parseInt(myList.get(i).getQuantity().replace("\"", ""));
            int basicPrice = Integer.parseInt(myList.get(i).getPrice().replace("\"", ""));
            total = total + quantity * basicPrice;
        }
        mTotalBillPrice.setText("" + total + " Rs.");

        if (myList.isEmpty()) {
            mEmptyLl.setVisibility(View.VISIBLE);
            mLl.setVisibility(View.GONE);
        } else {
            mLl.setVisibility(View.VISIBLE);
            mEmptyLl.setVisibility(View.GONE);
            //  itemslist = new ArrayList<BookedItems>();
            mBookedRv.setHasFixedSize(true);
            mBookedItemsAdapter = new BookedItemsAdapter(this, myList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mBookedRv.setLayoutManager(mLayoutManager);
            mBookedRv.setAdapter(mBookedItemsAdapter);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.act_booked_items_refresh_ib:

                updateList=mBookedItemsAdapter.getArrayList();
                for(int i = 0; i<updateList.size(); i++) {
                    int price = updateList.get(i).getUpdatePrice();
                    int updateQun= Integer.parseInt(updateList.get(i).getUpdateQuantity().toString());

                    tota=price*updateQun;
                }
                mTotalBillPrice.setText("" + tota + " Rs.");

                return;
        }
    }
}
