package com.bitplay.restpos.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bitplay.restpos.R;
import com.bitplay.restpos.extra.BookedItems;
import com.bitplay.restpos.extra.MealDetails;
import com.bitplay.restpos.views.activities.TableDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivek yadav on 26-10-2017.
 */

public class ItemArrayAdapter extends RecyclerView.Adapter<ItemArrayAdapter.ViewHolder> {

    private List<String> data;
    private Context mContext;
    private final CatogeryonClick mClick;
    private final int pos;
    private int row_index=-1;

    public interface CatogeryonClick {
        void onClicked(String data, int pos);

    }

    public ItemArrayAdapter(Context context, int pos, List<String> data, CatogeryonClick mClick) {
        this.mContext = context;
        this.pos = pos;
        this.data = data;
        this.mClick = mClick;
    }


    @Override
    public ItemArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.table_recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemArrayAdapter.ViewHolder holder, final int position) {


        holder.item_Name.setText(data.get(position).toString().replace("\"",""));
        holder.item_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mClick.onClicked(data.get(position), position);
                row_index=position;
                notifyDataSetChanged();

            }
        });
        if(row_index==position){
            holder.item_card.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
            holder.item_Name.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
        }
        else
        {
            holder.item_card.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.item_Name.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        }

    }


    @Override
    public int getItemCount() {
        return data.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView item_Name;
        public LinearLayout item_card;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setClickable(true);
            item_Name = (TextView) itemView.findViewById(R.id.table_item_meal_name_tv);
            item_card = (LinearLayout) itemView.findViewById(R.id.caption_linear_view_item);

        }
    }

}