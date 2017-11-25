package com.bitplay.restpos.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitplay.restpos.extra.BookedItems;
import com.bitplay.restpos.R;
import com.bitplay.restpos.extra.UpdateItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivek yadav on 26-10-2017.
 */

public class BookedItemsAdapter extends RecyclerView.Adapter<BookedItemsAdapter.ViewHolder> {

    private Context mContext;

    public List<BookedItems> itemslist;

    public List<UpdateItems> updateItemsList;


    public BookedItemsAdapter(Context context, List<BookedItems> itemslist) {
        this.mContext = context;
        this.itemslist = itemslist;
        // this.name=name;
        updateItemsList = new ArrayList<UpdateItems>();

    }



    @Override
    public BookedItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.booked_recyclerview_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BookedItemsAdapter.ViewHolder holder, final int position) {


        holder.item_Name.setText((itemslist.get(position).getItemName()).replace("\"", ""));
        holder.quality.setText(itemslist.get(position).getQuantity());
        holder.count.setText(String.valueOf(position + 1) + ".");

        holder.editMoreIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.quality.setFocusable(true);
                holder.quality.setEnabled(true);
                holder.quality.setCursorVisible(true);
                holder.editDoneIv.setVisibility(View.VISIBLE);
                holder.editMoreIV.setVisibility(View.GONE);

            }
        });
        holder.editDoneIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.quality.setFocusable(false);
                holder.quality.setEnabled(false);
                holder.quality.setCursorVisible(false);

                holder.editDoneIv.setVisibility(View.GONE);
                holder.editMoreIV.setVisibility(View.VISIBLE);

                String name=itemslist.get(position).getItemName().replace("\"", "");
                String price=itemslist.get(position).getPrice().replace("\"", "");


                UpdateItems upItems=new UpdateItems();
                upItems.setUpdateItemName(name);
                upItems.setUpdatePrice(Integer.parseInt(price));
                upItems.setUpdateQuantity(holder.quality.getText());
                updateItemsList.add(upItems);
                Log.d("BookItemAdapter","comming"+holder.quality.getText().toString());

            }
        });

    }

    public ArrayList<UpdateItems> getArrayList() {
        return (ArrayList<UpdateItems>) updateItemsList;
    }


    @Override
    public int getItemCount() {
        return itemslist.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView item_Name, count;
        public EditText quality;
        public ImageView editMoreIV,editDoneIv;

        public ViewHolder(final View itemView) {
            super(itemView);

            itemView.setClickable(true);
            item_Name = (TextView) itemView.findViewById(R.id.namee);
            quality = (EditText) itemView.findViewById(R.id.quality);
            count = (TextView) itemView.findViewById(R.id.item_count_tv);
            editMoreIV = (ImageView) itemView.findViewById(R.id.item_setting_iv);
            editDoneIv=(ImageView)itemView.findViewById(R.id.item_setting_done_iv);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    //    int position = getLayoutPosition();

                    delete(getAdapterPosition());
                    Toast.makeText(mContext, "" + item_Name.getText().toString() + " Delected", Toast.LENGTH_SHORT).show();
                    return true;
                }

                private void delete(int position) {

                    itemslist.remove(position);
                    notifyItemRemoved(position);
                }
            });

        }
    }

}