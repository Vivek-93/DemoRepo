package com.bitplay.restpos.views.fragments.caption;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bitplay.restpos.R;
import com.bitplay.restpos.adapters.CaptionSearchAdapter;
import com.bitplay.restpos.adapters.SubItemArrayAdapter;
import com.bitplay.restpos.extra.MealDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaptionSearchFragment extends Fragment implements View.OnClickListener {

    ArrayList<MealDetails> searchList = new ArrayList<>();

    public AutoCompleteTextView mSearchEt;
    public ImageView mSearchIv;
    public RecyclerView mCaptionSearchRv;
    private List<String> searchDataList = new ArrayList<String>();
    private CaptionSearchAdapter mCaptionSearchAdapter;
    private List<String> mSearchedItemsList = new ArrayList<>();
    private String searchItemm;
    private String search;

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
        searchList = (ArrayList<MealDetails>) getArguments().getSerializable("itemList");
        Log.d("captionSearchFragment", "List" + searchList.get(0).getItemName().toString());
        initilizeView();

        return view;
    }

    private void initilizeView() {
        mSearchIv.setOnClickListener(this);
        for (int i = 0; i < searchList.size(); i++) {
            String itemName = searchList.get(i).getItemName().toString().replace("\"", "");
            searchDataList.add(itemName);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, searchDataList);
        //  mSearchEt.setThreshold(6);
        mSearchEt.setThreshold(1);
        mSearchEt.setAdapter(adapter);

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
      /*  } else {
            Toast.makeText(getContext(), "Data is not presrnt", Toast.LENGTH_SHORT).show();
        }
*/
    }


   /* public static void hideKeyboard(Context context) {
        try {
            ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            if ((((Activity) context).getCurrentFocus() != null) && (((Activity) context).getCurrentFocus().getWindowToken() != null)) {
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showKeyboard(Context context) {
        ((InputMethodManager) (context).getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }*/

}
