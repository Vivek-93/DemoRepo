package com.bitplay.restpos.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitplay.restpos.R;
import com.bitplay.restpos.extra.SearchData;

import java.util.List;

/**
 * Created by vivek yadav on 26-10-2017.
 */

public class CaptionSearchAdapter extends RecyclerView.Adapter<CaptionSearchAdapter.ViewHolder> {

    private Context mContext;
    private List<SearchData> mSearch;


    public CaptionSearchAdapter(Context context, List<SearchData> search) {
        this.mContext = context;
        this.mSearch = search;

    }


    @Override
    public CaptionSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.caption_search_recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CaptionSearchAdapter.ViewHolder holder, final int position) {

        holder.mSerailNo.setText("" + (position + 1) + ".");
        holder.mSearchItemName.setText(mSearch.get(position).getItemName().toString());

        Log.d("CaptionSearchAdapter","quantity"+mSearch.get(position).getQuantity());
        holder.mQuantity.setText(""+mSearch.get(position).getQuantity());

    }


    @Override
    public int getItemCount() {
        return mSearch.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mSearchItemName, mSerailNo ,mQuantity;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setClickable(true);
            mSearchItemName = (TextView) itemView.findViewById(R.id.fragment_caption_search_item_tv);
            mSerailNo = (TextView) itemView.findViewById(R.id.fragment_caption_search_serial_tv);
            mQuantity=(TextView)itemView.findViewById(R.id.fragment_caption_search_item_quantity_tv);

        }
    }

}