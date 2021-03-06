package com.bitplay.restpos.views.fragments.caption;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bitplay.restpos.R;
import com.bitplay.restpos.adapters.CaptionSearchAdapter;
import com.bitplay.restpos.adapters.SubItemArrayAdapter;
import com.bitplay.restpos.extra.BookedItems;
import com.bitplay.restpos.extra.MealDetails;
import com.bitplay.restpos.extra.SearchData;
import com.bitplay.restpos.interfaces.menucategory.MenuCategoryPresenterImpl;
import com.bitplay.restpos.interfaces.searchitem.ISearchItemView;
import com.bitplay.restpos.interfaces.searchitem.SearchItemPresenterImpl;
import com.bitplay.restpos.models.searchitem.SearchItemModel;
import com.bitplay.restpos.utils.Utils;
import com.bitplay.restpos.views.activities.TableDetailsActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bitplay.restpos.utils.Utils.context;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaptionSearchFragment extends Fragment implements View.OnClickListener, ISearchItemView {

    private ArrayList<MealDetails> searchList = new ArrayList<>();
    public AutoCompleteTextView mSearchEt;
    public ImageView mSearchIv;
    public RecyclerView mCaptionSearchRv;
    private List<SearchItemModel> searchDataList ;
    private CaptionSearchAdapter mCaptionSearchAdapter;
    private List<SearchData> mSearchedItemsList;
    private String searchItemm;
    public String search;
    private Dialog additemsDialog;
    private Spinner itemSpinner;
    private ArrayList<String> addQuantity;
    private SearchData mSearchData;

    private Context mContext;
    private TableDetailsActivity mTableDetailsActivity;
    private FragmentManager mFragmentManager;
    private SearchItemPresenterImpl mSubItemPresenter;
    private String items;
    private ArrayList<String> list;
    private ArrayAdapter adapter;

    public CaptionSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((InputMethodManager) (getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        View view = inflater.inflate(R.layout.fragment_caption_search, container, false);
        mSearchEt = (AutoCompleteTextView) view.findViewById(R.id.fragment_caption_search_et);
        mSearchIv = (ImageView) view.findViewById(R.id.fragment_caption_search_iv);
        mCaptionSearchRv = (RecyclerView) view.findViewById(R.id.fragment_caption_search_rv);
        //  searchList = (ArrayList<MealDetails>) getArguments().getSerializable("itemList");
        //  Log.d("captionSearchFragment", "List" + searchList.get(0).getItemName().toString());
        initilizeView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        mTableDetailsActivity = (TableDetailsActivity) getActivity();
        mFragmentManager = mTableDetailsActivity.getSupportFragmentManager();
        mSubItemPresenter = new SearchItemPresenterImpl(this);
        mSubItemPresenter.searchItemsApi(1);

        Log.d("CaptionSearhFragment","on view create list"+searchDataList.size());

    }


    private void initilizeView() {
        searchDataList = new ArrayList<>();
        mSearchedItemsList = new ArrayList<SearchData>();
        list = new ArrayList<String>();
        mSearchIv.setOnClickListener(this);

        Log.d("CaptionSearhFragment","iniz list"+searchDataList.size());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_caption_search_iv:
                searchFunction();
                break;
        }
    }

    private void searchFunction() {

        search = mSearchEt.getText().toString();
        additemsDialog = new Dialog(getContext());
        additemsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        additemsDialog.setContentView(R.layout.item_table_detail_item_quantity_bill);
        additemsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        additemsDialog.getWindow().setGravity(Gravity.CENTER);
        additemsDialog.show();

        TextView itemQuality = (TextView) additemsDialog.findViewById(R.id.item_table_details_quantity_tv);
        Button addBucket = (Button) additemsDialog.findViewById(R.id.item_table_details_quantity_button);
        itemSpinner = (Spinner) additemsDialog.findViewById(R.id.item_table_details_quantity_spinner);
        // itemQuality.setText((data.get(position).getItemName().toString()).replace("\"", ""));
        itemQuality.setText(search);

        addQuantity = new ArrayList<String>();
        addQuantity.add("" + 1);
        addQuantity.add("" + 2);
        addQuantity.add("" + 3);
        addQuantity.add("" + 4);
        addQuantity.add("" + 5);
        addQuantity.add("" + 6);
        addQuantity.add("" + 7);
        addQuantity.add("" + 8);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, addQuantity);
        itemSpinner.setAdapter(adapter);
        addBucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < searchDataList.size(); i++) {
                    searchItemm = searchDataList.get(i).toString();

                }
                mSearchData = new SearchData();
                mSearchData.setItemName(search);
                mSearchData.setQuantity(Integer.parseInt(itemSpinner.getSelectedItem().toString()));
                mSearchedItemsList.add(mSearchData);

                mCaptionSearchRv.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                mCaptionSearchRv.setLayoutManager(layoutManager);
                mCaptionSearchAdapter = new CaptionSearchAdapter(getContext(), mSearchedItemsList);
                mCaptionSearchRv.setAdapter(mCaptionSearchAdapter);
                mSearchEt.setText("");
                additemsDialog.dismiss();
            }
        });



       /* search = mSearchEt.getText().toString();
        for (int i = 0; i < searchDataList.size(); i++) {
            searchItemm = searchDataList.get(i).toString();
            Log.d("Caption", "item" + searchItemm);
        }

        mSearchedItemsList.add(search);
        mCaptionSearchRv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mCaptionSearchRv.setLayoutManager(layoutManager);
        mCaptionSearchAdapter = new CaptionSearchAdapter(getContext(), mSearchedItemsList);
        mCaptionSearchRv.setAdapter(mCaptionSearchAdapter);
        mSearchEt.setText("");
*/
    }

    @Override
    public void onSearchItemsApiSuccess(int pid, SearchItemModel[] searchItemModels) {
        Utils.stopProgress(context);
        searchDataList = Arrays.asList(searchItemModels);
        Log.d("CaptionSearhFragment","direct list"+searchDataList.size());

        for (int i = 0; i < searchDataList.size(); i++) {
            list.add(searchDataList.get(i).getImageName().toString());
        }

        Log.d("CaptionSearhFragment","list"+list.size());





      /*  Log.d("CaptionSearhFragment",""+list.size());
        adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, list);
        adapter.notifyDataSetChanged();
        mSearchEt.setThreshold(1);
        mSearchEt.setAdapter(adapter);*/




    }

    @Override
    public void onSearchItemsApiError(int pid, String error) {

    }
}
