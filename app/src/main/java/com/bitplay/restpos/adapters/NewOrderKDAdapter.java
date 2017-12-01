package com.bitplay.restpos.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitplay.restpos.R;

import java.util.List;

/**
 * Created by vivek yadav on 26-10-2017.
 */

public class NewOrderKDAdapter extends RecyclerView.Adapter<NewOrderKDAdapter.ViewHolder> {

    private Context mContext;


    public NewOrderKDAdapter(Context context) {
        this.mContext = context;

    }


    @Override
    public NewOrderKDAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.new_order_kd_recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NewOrderKDAdapter.ViewHolder holder, final int position) {


    }


    @Override
    public int getItemCount() {
        return 10;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView item_Name;
        public LinearLayout item_card;


        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setClickable(true);
           /* item_Name = (TextView) itemView.findViewById(R.id.table_item_meal_name_tv);
            item_card = (LinearLayout) itemView.findViewById(R.id.caption_linear_view_item);*/

        }
    }

}