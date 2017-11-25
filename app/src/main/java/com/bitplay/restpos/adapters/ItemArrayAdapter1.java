package com.bitplay.restpos.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.bitplay.restpos.extra.BookedItems;
import com.bitplay.restpos.extra.MealDetails;
import com.bitplay.restpos.views.activities.TableDetailsActivity;
import com.bitplay.restpos.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivek yadav on 26-10-2017.
 */

public class ItemArrayAdapter1 extends RecyclerView.Adapter<ItemArrayAdapter1.ViewHolder> {

    ArrayList<MealDetails> data;
    private Context mContext;

    private Dialog additemsDialogBox;
    private Spinner itemQuantitySpinner;

    String quantity, itemName;
    private List<String> addQuantity;
    public static List<BookedItems> bookeditemsList = new ArrayList<BookedItems>();


    public ItemArrayAdapter1(Context context, ArrayList<MealDetails> data) {
        this.mContext = context;
        this.data = data;
    }

    public ItemArrayAdapter1(Context context, List<BookedItems> bookeditemsList) {
        this.mContext = context;
        this.bookeditemsList = bookeditemsList;

    }



    @Override
    public ItemArrayAdapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.table_recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemArrayAdapter1.ViewHolder holder, final int position) {





              //  holder.item_Name.setText("" + (data.get(position).getItemName().toString()).replace("\"",""));
                //  holder.short_Code.setText(" Short Code : " + data.get(position).getShortCode().toString());
                //   holder.online_Display_Name.setText(" Online Display Name : " + data.get(position).getOnlineDisplayName().toString());
                holder.category.setText("" + data.get(position).getCategory().toString());
               // holder.price.setText("" + (data.get(position).getPrice().toString()).replace("\"",""));
              //  holder.minimum_Preparation_Time.setText("" + data.get(position).getMinimumPreparationTime().toString());
                //  holder.hSN_Code.setText(" HSN Code : " + data.get(position).gethSNCode().toString());
              //  holder.description.setText("" + data.get(position).getDescription().toString());
              //  holder.available.setText("" + (data.get(position).getAvailable().toString()).replace("\"",""));
              //  holder.green.setVisibility(View.GONE);
             //   holder.red.setVisibility(View.VISIBLE);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    additemsDialogBox = new Dialog(mContext);
                    additemsDialogBox.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    additemsDialogBox.setContentView(R.layout.item_table_detail_item_quantity_bill);
                    additemsDialogBox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    additemsDialogBox.getWindow().setGravity(Gravity.CENTER);
                    additemsDialogBox.show();
                    TextView itemQuality = (TextView) additemsDialogBox.findViewById(R.id.item_table_details_quantity_tv);
                    Button addBucket = (Button) additemsDialogBox.findViewById(R.id.item_table_details_quantity_button);
                    itemQuantitySpinner = (Spinner) additemsDialogBox.findViewById(R.id.item_table_details_quantity_spinner);
                    itemQuality.setText((data.get(position).getItemName().toString()).replace("\"",""));

                    addQuantity = new ArrayList<String>();
                    addQuantity.add("" + 1);
                    addQuantity.add("" + 2);
                    addQuantity.add("" + 3);
                    addQuantity.add("" + 4);
                    addQuantity.add("" + 5);
                    addQuantity.add("" + 6);
                    addQuantity.add("" + 7);
                    addQuantity.add("" + 8);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, addQuantity);
                    itemQuantitySpinner.setAdapter(adapter);

                    addBucket.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            quantity = itemQuantitySpinner.getSelectedItem().toString();
                            itemName = data.get(position).getItemName().toString();
                            String price=data.get(position).getPrice().toString();

                            BookedItems bookedItems = new BookedItems();
                            bookedItems.setItemName(itemName);
                            bookedItems.setQuantity(quantity);
                            bookedItems.setPrice(price);
                            bookeditemsList.add(bookedItems);
                            Log.d("list", "" + bookeditemsList.size());
                            Intent intent = new Intent(mContext, TableDetailsActivity.class);
                            intent.setFlags( Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NO_HISTORY);
                            mContext.startActivity(intent);

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public ArrayList<BookedItems> getArrayList() {
        return (ArrayList<BookedItems>) bookeditemsList;
    }

    @Override
    public int getItemCount() {
        return data.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_Name, short_Code, online_Display_Name, category, price, minimum_Preparation_Time, hSN_Code, description, available;

        ImageView red, green;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setClickable(true);
         /*   item_Name = (TextView) itemView.findViewById(R.id.table_item_meal_name_tv);
            short_Code = (TextView) itemView.findViewById(R.id.table_item_meal_short_code_tv);
            online_Display_Name = (TextView) itemView.findViewById(R.id.table_item_meal_online_name_tv);
            category = (TextView) itemView.findViewById(R.id.table_item_meal_catogery_tv);
            price = (TextView) itemView.findViewById(R.id.table_item_meal_price_tv);
            minimum_Preparation_Time = (TextView) itemView.findViewById(R.id.table_item_meal_mpt_tv);
            hSN_Code = (TextView) itemView.findViewById(R.id.table_item_meal_HSN_Code_tv);
            description = (TextView) itemView.findViewById(R.id.table_item_meal_description_tv);
            available = (TextView) itemView.findViewById(R.id.table_item_meal_available_tv);
            green = (ImageView) itemView.findViewById(R.id.table_item_meal_cotogory_green_iv);
            red = (ImageView) itemView.findViewById(R.id.table_item_meal_cotogory_red_iv);*/
        }
    }

}