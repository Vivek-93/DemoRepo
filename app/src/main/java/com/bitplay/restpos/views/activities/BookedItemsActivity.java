package com.bitplay.restpos.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitplay.restpos.adapters.BookedItemsAdapter;
import com.bitplay.restpos.extra.BookedItems;
import com.bitplay.restpos.R;
import com.bitplay.restpos.extra.UpdateItems;
import com.bitplay.restpos.interfaces.bookeddetail.BookedDetailPresenteImpl;
import com.bitplay.restpos.interfaces.bookeddetail.IBookedDetailPresenter;
import com.bitplay.restpos.interfaces.bookeddetail.IBookedDetailView;
import com.bitplay.restpos.models.bookeddetail.BookedDetailModel;
import com.bitplay.restpos.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookedItemsActivity extends AppCompatActivity implements View.OnClickListener, IBookedDetailView {

    public RecyclerView mBookedRv;
    public LinearLayout mEmptyLl, mLl;
    private BookedItemsAdapter mBookedItemsAdapter;
    public TextView mTotalBillPrice;
    public ImageButton mRefresh;

    private IBookedDetailPresenter mIBookedDetailPresenter;
    private int total;
    private int tota;

    private List<BookedDetailModel> mBookedItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_items);
        mBookedRv = (RecyclerView) findViewById(R.id.act_booked_table_items_rv);
        mEmptyLl = (LinearLayout) findViewById(R.id.act_booked_item_empty_ll);
        mLl = (LinearLayout) findViewById(R.id.act_booked_item_ll);
        mTotalBillPrice = (TextView) findViewById(R.id.act_booked_items_totalprice_tv);
        mRefresh = (ImageButton) findViewById(R.id.act_booked_items_refresh_ib);

        mIBookedDetailPresenter = new BookedDetailPresenteImpl(BookedItemsActivity.this);
        initilizeView();
    }

    private void initilizeView() {

        mIBookedDetailPresenter.bookedDetailsApi(1);
        mRefresh.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.act_booked_items_refresh_ib:


                return;
        }
    }

    @Override
    public void onBookedDetailsApiSuccess(int pid, BookedDetailModel[] bookedDetailModel) {
        Utils.stopProgress(BookedItemsActivity.this);
        Log.d("BookedItemsActivity", "hi");
        mBookedItemList = Arrays.asList(bookedDetailModel);

        Log.d("BookedItemsActivity", ""+mBookedItemList.size());
        mBookedRv.setHasFixedSize(true);
        mBookedItemsAdapter = new BookedItemsAdapter(this, mBookedItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mBookedRv.setLayoutManager(mLayoutManager);
        mBookedRv.setAdapter(mBookedItemsAdapter);
    }

    @Override
    public void onBookedDetailsApiError(int pid, String bookedDetailModelError) {
        Utils.stopProgress(BookedItemsActivity.this);

        Toast.makeText(this, ""+bookedDetailModelError, Toast.LENGTH_SHORT).show();

    }
}
