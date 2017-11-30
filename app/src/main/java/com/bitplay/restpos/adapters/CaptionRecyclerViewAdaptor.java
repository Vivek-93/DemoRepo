package com.bitplay.restpos.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitplay.restpos.views.activities.BillPdfViewActivity;
import com.bitplay.restpos.views.activities.MainActivity;
import com.bitplay.restpos.views.activities.TableDetailsActivity;
import com.bitplay.restpos.R;

/**
 * Created by Vivek yadav on 18-10-2017.
 */

public class CaptionRecyclerViewAdaptor extends RecyclerView.Adapter<CaptionRecyclerViewAdaptor.ViewHolder> {

    MainActivity homeActivity;
    String[] data;
    private Context mContext;

    private Dialog printDialogBox, tableInfoDialogBox;
    private TextView counts;
    private ImageView decreseIv, increseIv;
    private Button proceedBtn;
    int counter = 0;
    private EditText guestName,guestPhone,guestTable;


    public CaptionRecyclerViewAdaptor(Context context, String[] data) {
        this.mContext = context;
        this.data = data;

    }

    @Override
    public CaptionRecyclerViewAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.caption_recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CaptionRecyclerViewAdaptor.ViewHolder holder, final int position) {
        String table = data[position];
        holder.myTextView.setText(table);

        if ( position==3) {
            holder.itemView.setBackgroundColor(Color.YELLOW);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mContext,TableDetailsActivity.class);
                    mContext.startActivity(intent);
                }
            });

        }else if(position==4){
            holder.itemView.setBackgroundColor(Color.GREEN);
            holder.itemView.setClickable(false);
        }else if(position==5){
            holder.itemView.setBackgroundColor(Color.GRAY);
            holder.itemView.setClickable(false);
        }else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    tableInfoDialogBox = new Dialog(mContext);
                    //  tableInfoDialogBox.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    tableInfoDialogBox.setContentView(R.layout.item_caption_table_info);
                    tableInfoDialogBox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    tableInfoDialogBox.show();
                    decreseIv = (ImageView) tableInfoDialogBox.findViewById(R.id.caption_table_decrese_count);
                    increseIv = (ImageView) tableInfoDialogBox.findViewById(R.id.caption_table_increse_count);
                    counts = (TextView) tableInfoDialogBox.findViewById(R.id.caption_table_count);
                    guestName=(EditText)tableInfoDialogBox.findViewById(R.id.caption_table_guest_name_et);
                    guestPhone=(EditText)tableInfoDialogBox.findViewById(R.id.caption_table_guest_phone_et);
                    guestTable=(EditText)tableInfoDialogBox.findViewById(R.id.caption_table_guest_table_et);
                    proceedBtn = (Button) tableInfoDialogBox.findViewById(R.id.caption_table_proceed_btn);

                    try {
                        decreseIv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                if (counter > 0) {
                                    counter--;
                                    counts.setText("" + counter);
                                } else if (counter < 0) {
                                    Toast.makeText(homeActivity, "Add head counts", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        increseIv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (counter >= 0 && counter < 10) {
                                    counter++;
                                    counts.setText("" + counter);
                                } else if (counter > 10) {
                                    Toast.makeText(homeActivity, "No space on this table", Toast.LENGTH_SHORT).show();
                                } else if (counter < 0) {
                                    Toast.makeText(homeActivity, "Select any no.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    proceedBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, TableDetailsActivity.class);
                            intent.putExtra("guestName", guestName.getText().toString());
                            intent.putExtra("guestPhone", guestPhone.getText().toString());
                            intent.putExtra("guestTable", guestTable.getText().toString());
                            mContext.startActivity(intent);
                            tableInfoDialogBox.dismiss();
                        }
                    });

                }
            });
        }

        holder.printBillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printDialogBox = new Dialog(mContext);
                printDialogBox.requestWindowFeature(Window.FEATURE_NO_TITLE);
                printDialogBox.setContentView(R.layout.item_caption_table_bill);
                printDialogBox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                printDialogBox.getWindow().setGravity(Gravity.CENTER);
                printDialogBox.show();

                TextView mCancelTV = (TextView) printDialogBox.findViewById(R.id.edit_dialog_cancel_tv);
                TextView mPrintBillTV = (TextView) printDialogBox.findViewById(R.id.edit_dialog_print_tv);

                mPrintBillTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        printBillInPDF();
                        printDialogBox.dismiss();
                    }
                });

                mCancelTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        printDialogBox.dismiss();
                    }
                });
            }


        });

    }

    private void printBillInPDF() {


        Intent intent=new Intent(mContext, BillPdfViewActivity.class);
        mContext.startActivity(intent);

    }


    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView myTextView;
        public Button printBillBtn;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.table_info);
            cardView = (CardView) itemView.findViewById(R.id.caption_card_view_item);
            printBillBtn = (Button) itemView.findViewById(R.id.caption_rv_billprint_btn);
        }
    }
}
