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

/**
 * A simple {@link Fragment} subclass.
 */
public class NewOrderKDFragment extends Fragment {

    public RecyclerView mRecyclerView;
    private NewOrderKDAdapter mNewOrderKDAdapter;

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
    }

    private void settingUpRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mNewOrderKDAdapter = new NewOrderKDAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mNewOrderKDAdapter);

    }

}
