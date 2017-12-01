package com.bitplay.restpos.views.fragments.caption;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bitplay.restpos.R;
import com.bitplay.restpos.adapters.ItemArrayAdapter;
import com.bitplay.restpos.adapters.ItemArrayAdapter1;
import com.bitplay.restpos.adapters.SubItemArrayAdapter;
import com.bitplay.restpos.adapters.SubSubItemAdapter;
import com.bitplay.restpos.extra.MealDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaptionCatogeryFragment extends Fragment {

    public RecyclerView mCatogeryRecyclerView, mSubSubCatogeryRv,mSubCatogeryRv;
    private ArrayList<MealDetails> mList = new ArrayList<>();
    private List<String> catogeryList = new ArrayList<>();
    {
        catogeryList.add("Break fast");
        catogeryList.add("Lunch");
        catogeryList.add("Eve meal");
        catogeryList.add("Dinner");

    }
    private List<String> subCatogeryList=new ArrayList<String>();
    {
        subCatogeryList.add("Beverages");
        subCatogeryList.add("Burger");
        subCatogeryList.add("Chinese Main Course");
        subCatogeryList.add("Chinese Starter");
        subCatogeryList.add("Chinese Soups");
        subCatogeryList.add("Main Course Lamb");
        subCatogeryList.add("Kitchen Beverages");
        subCatogeryList.add("Frecnch Roll Sandwich");
        subCatogeryList.add("Main Course Chicken");

    }

    private int pos;
    private int position;
    private ItemArrayAdapter mItemArrayAdapter;
    private SubItemArrayAdapter mSubItemArrayAdapter;
    private SubSubItemAdapter mSubSubItemAdapter;

    public CaptionCatogeryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caption_catogery, container, false);
        mCatogeryRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_caption_catogery_rv);
        mSubSubCatogeryRv = (RecyclerView) view.findViewById(R.id.fragment_caption_sub_sub_catogery_rv);
        mSubCatogeryRv=(RecyclerView)view.findViewById(R.id.fragment_caption_sub_catogery_rv);
        mList = (ArrayList<MealDetails>) getArguments().getSerializable("itemlistCatogery");
        initializeView();
        return view;
    }

    private void initializeView() {

        mCatogeryRecyclerView.setHasFixedSize(true);
        settingUpRecyclerView();

    }

    private void settingUpRecyclerView() {
        mItemArrayAdapter = new ItemArrayAdapter(getContext(), pos, catogeryList, new ItemArrayAdapter.CatogeryonClick() {
            @Override
            public void onClicked(String catogery, int pos) {
                Log.d("CaptionCatogery", "" + pos);


                mSubSubItemAdapter =new SubSubItemAdapter(getContext(), position, subCatogeryList, new SubSubItemAdapter.SubCatogeryonClick() {
                    @Override
                    public void onClicked(String data, int pos) {

                        Log.d("CaptionFragment",""+data);
                        mSubSubCatogeryRv.setHasFixedSize(true);
                        mSubItemArrayAdapter = new SubItemArrayAdapter(getContext(), mList, data);
                        mSubSubCatogeryRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
                        mSubSubCatogeryRv.setAdapter(mSubItemArrayAdapter);
                    }
                });
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                mSubCatogeryRv.setLayoutManager(mLayoutManager);
                mSubCatogeryRv.setAdapter(mSubSubItemAdapter);

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mCatogeryRecyclerView.setLayoutManager(mLayoutManager);
        mCatogeryRecyclerView.setAdapter(mItemArrayAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
