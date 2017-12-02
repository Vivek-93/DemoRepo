package com.bitplay.restpos.views.fragments.kitchendisplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bitplay.restpos.R;
import com.bitplay.restpos.adapters.NewOrderKDAdapter;
import com.bitplay.restpos.adapters.SubItemArrayAdapter;
import com.bitplay.restpos.extra.NewOrderList;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewOrderKDFragment extends Fragment {

    public RecyclerView mRecyclerView;
    private NewOrderKDAdapter mNewOrderKDAdapter;
    private List<NewOrderList> newOrderList= new ArrayList<NewOrderList>();;

    public NewOrderKDFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_order_kd, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.frag_kd_new_order_rv);
        initializeView();
        return view;
    }

    private void initializeView() {
        settingUpRecyclerView();

        createAList();


    }

    private void createAList() {
        NewOrderList mOrder=new NewOrderList();
        mOrder.setItem_name("Pizza");
        mOrder.setKot("KOT2");
        mOrder.setQuantity("3");
        mOrder.setTable_no("Table6");
        mOrder.setOrder_time("2:34");
        mOrder.setTime_elapsed("2");
        mOrder.setOrder_taken_by("Vivek");
        newOrderList.add(mOrder);
    }

    private void settingUpRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mNewOrderKDAdapter = new NewOrderKDAdapter(getContext(),newOrderList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mNewOrderKDAdapter);

    }

}
