package com.bitplay.restpos.views.fragments.caption;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.bitplay.restpos.adapters.SubItemArrayAdapter;
import com.bitplay.restpos.adapters.SubSubItemAdapter;
import com.bitplay.restpos.extra.MealDetails;
import com.bitplay.restpos.interfaces.menucategory.IMenuCategoryView;
import com.bitplay.restpos.interfaces.menucategory.MenuCategoryPresenterImpl;
import com.bitplay.restpos.models.menucategory.MenuCategoryModel;
import com.bitplay.restpos.models.subcategory.SubcategoryModel;
import com.bitplay.restpos.models.subcategoryitem.SubCategoryItemModel;
import com.bitplay.restpos.utils.Sharedpreferences;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.activities.TableDetailsActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bitplay.restpos.utils.Utils.context;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaptionCatogeryFragment extends Fragment implements IMenuCategoryView {

    public RecyclerView mCatogeryRecyclerView, mSubSubCatogeryRv, mSubCatogeryRv;
    private List<SubCategoryItemModel> mList = new ArrayList<>();
    private List<MenuCategoryModel> catogeryList = new ArrayList<>();

    private List<SubcategoryModel> subCatogeryList = new ArrayList<SubcategoryModel>();

    private int pos;
    private int position;
    private ItemArrayAdapter mItemArrayAdapter;
    private SubItemArrayAdapter mSubItemArrayAdapter;
    private SubSubItemAdapter mSubSubItemAdapter;

    private Context mContext;
    private TableDetailsActivity mTableDetailsActivity;
    private FragmentManager mFragmentManager;
    private MenuCategoryPresenterImpl mMenuCategoryPresenter;
    private String tableno;
    public Sharedpreferences mPref;
    public CaptionCatogeryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caption_catogery, container, false);
        mCatogeryRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_caption_catogery_rv);
        mSubSubCatogeryRv = (RecyclerView) view.findViewById(R.id.fragment_caption_sub_sub_catogery_rv);
        mSubCatogeryRv = (RecyclerView) view.findViewById(R.id.fragment_caption_sub_catogery_rv);
   //     tableno = getArguments().getString("tableno");
        initializeView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        mTableDetailsActivity = (TableDetailsActivity) getActivity();
        mFragmentManager = mTableDetailsActivity.getSupportFragmentManager();
        mMenuCategoryPresenter = new MenuCategoryPresenterImpl(this);
        mMenuCategoryPresenter.getMenuCategoryApi();
    }

    private void initializeView() {
        mPref = Sharedpreferences.getUserDataObj(getActivity());
        mCatogeryRecyclerView.setHasFixedSize(true);
        settingUpRecyclerView();

    }

    private void settingUpRecyclerView() {
        mItemArrayAdapter = new ItemArrayAdapter(getContext(), pos, catogeryList, new ItemArrayAdapter.CatogeryonClick() {
            @Override
            public void onClicked(MenuCategoryModel catogery, int pos) {
                mMenuCategoryPresenter.getSubCategoryApi(catogery.getCategory());
                // settingUpCategoryRecyclerView(catogery, pos);

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

    @Override
    public void getMenuCategoryApiSuccess(int pid, MenuCategoryModel[] menuCategoryModel) {

        Utils.stopProgress(context);
        catogeryList = Arrays.asList(menuCategoryModel);
        if (menuCategoryModel != null) {
            settingUpRecyclerView();
        } else {
            Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void getMenuCategoryApiError(int pid, String errorData) {


    }

    @Override
    public void onGetSubCategorySuccess(int pid, SubcategoryModel[] subcategoryModels) {

        Utils.stopProgress(context);
        Log.d("CCF", "list size" + subcategoryModels.length);
        subCatogeryList = Arrays.asList(subcategoryModels);
        mSubSubItemAdapter = new SubSubItemAdapter(getContext(), pos, subCatogeryList, new SubSubItemAdapter.SubCatogeryonClick() {
            @Override
            public void onClicked(SubcategoryModel data, int pos) {

                mMenuCategoryPresenter.getSubCategoryItemApi(data.getSubcategory());

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mSubCatogeryRv.setLayoutManager(mLayoutManager);
        mSubCatogeryRv.setAdapter(mSubSubItemAdapter);

    }

    @Override
    public void onGetSubCategoryError(int pid, String error) {

    }

    @Override
    public void onGetSubCategoryItemSuccess(int pid, SubCategoryItemModel[] subCategoryItemModel) {
        Utils.stopProgress(context);
        mList = Arrays.asList(subCategoryItemModel);
        mSubSubCatogeryRv.setHasFixedSize(true);
        mSubItemArrayAdapter = new SubItemArrayAdapter(getContext(), mList, new SubItemArrayAdapter.AddCartButtonClick() {

            @Override
            public void onClicked(String itemname, int quantity, float price) {
                mMenuCategoryPresenter.bookedOrderApi(1, itemname, quantity,price);
            }
        });
        mSubSubCatogeryRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mSubSubCatogeryRv.setAdapter(mSubItemArrayAdapter);
    }

    @Override
    public void onGetSubCategoryItemError(int pid, String error) {

    }

    @Override
    public void onOrderBookedSuccess(int pid, String orderBookedSuccess) {
        Utils.stopProgress(context);
        Toast.makeText(mContext, ""+orderBookedSuccess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOrderBookedError(int pid, String orderBookedError) {
        Utils.stopProgress(context);
        Toast.makeText(mContext, ""+orderBookedError, Toast.LENGTH_SHORT).show();

    }
}
