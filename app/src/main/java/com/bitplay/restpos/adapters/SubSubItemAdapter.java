package com.bitplay.restpos.adapters;


import android.content.Context;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bitplay.restpos.R;
import com.bitplay.restpos.extra.BookedItems;
import com.bitplay.restpos.extra.MealDetails;
import com.bitplay.restpos.models.subcategory.SubcategoryModel;
import com.bitplay.restpos.views.activities.TableDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivek yadav on 26-10-2017.
 */

public class SubSubItemAdapter extends RecyclerView.Adapter<SubSubItemAdapter.ViewHolder> {

    private List<SubcategoryModel> subData;
    private Context mContext;
    private final int position;
    private String mCatogery;
    private final SubCatogeryonClick mClick;
    private int row_index=-1;

    public interface SubCatogeryonClick {
        void onClicked(SubcategoryModel data, int pos);

    }


    public SubSubItemAdapter(Context context, int pos, List<SubcategoryModel> subData, SubCatogeryonClick mClick) {
        this.mContext = context;
        this.position = pos;
        this.subData = subData;
        this.mClick = mClick;
    }


    @Override
    public SubSubItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.table_recyclerview_sub_sub_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SubSubItemAdapter.ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mClick.onClicked(subData.get(position), position);
                row_index=position;
                notifyDataSetChanged();

            }
        });
        if(row_index==position){
            holder.mCardView.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
            holder.mItemName.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
        }
        else
        {
            holder.mCardView.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.mItemName .setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        }

        holder.mItemName.setText(subData.get(position).getSubcategory().toString());

    }

    @Override
    public int getItemCount() {
        return subData.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mItemName;
        public CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
           mItemName=(TextView)itemView.findViewById(R.id.caption_sub_sub_item_tv);
           mCardView=(CardView)itemView.findViewById(R.id.caption_card_view_sub_sub_item);

        }
    }

}