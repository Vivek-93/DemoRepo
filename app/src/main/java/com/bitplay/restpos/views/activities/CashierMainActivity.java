package com.bitplay.restpos.views.activities;

import android.Manifest;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bitplay.restpos.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class CashierMainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    public ImageView mHamBurgerIconIv;
    private DrawerLayout cashierHomeActDrawerLayout;
    public NavigationView mLeftNavView;

    public Button mTransferBtn,mReceiveBalance;
    public TextView mOpeningBalance,mClosingBalance,mDate;
    public DigitalClock mClock;
    private Dialog receiveSmsDialogBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_home_main);
        mHamBurgerIconIv=(ImageView)findViewById(R.id.mainCashierActHamBurgerIconIV);
        mTransferBtn=(Button)findViewById(R.id.act_cashier_main_transfer_button);
        mOpeningBalance=(TextView)findViewById(R.id.act_cashier_main_balance_real_tv);
        mClosingBalance=(TextView)findViewById(R.id.act_cashier_main_close_balance_real_tv);
        mDate=(TextView)findViewById(R.id.act_caisher_main_date_tv);
        mClock=(DigitalClock) findViewById(R.id.digital_clock);
        mReceiveBalance=(Button)findViewById(R.id.act_cashier_main_receive_button);


        initilizeView();

    }

    private void initilizeView() {
        settingLeftNavigtionView();
        settingClickListner();
        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        mDate.setText(currentDateTimeString);
        Random r = new Random();
        int i1 = r.nextInt(80000 - 6500) + 650;
        mClosingBalance.setText(""+i1+" Rs.");

    }

    private void settingClickListner() {
        mHamBurgerIconIv.setOnClickListener(this);
        mTransferBtn.setOnClickListener(this);
        mReceiveBalance.setOnClickListener(this);
    }

    private void settingLeftNavigtionView() {
        cashierHomeActDrawerLayout = (DrawerLayout) findViewById(R.id.cashier_main_act_drawer_layout);
        mLeftNavView = (NavigationView) findViewById(R.id.cashierMainActLeftNavView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainCashierActHamBurgerIconIV:
                cashierHomeActDrawerLayout.openDrawer(Gravity.LEFT);
                break;

            case R.id.act_cashier_main_transfer_button:
                sendClosingBalanceFunction();
                break;

            case R.id.act_cashier_main_receive_button:
                receiveClosingBalanceFunction();
                break;
        }
    }

    private void receiveClosingBalanceFunction() {

        try {
            receiveSmsDialogBox = new Dialog(this);
            receiveSmsDialogBox.requestWindowFeature(Window.FEATURE_NO_TITLE);
            receiveSmsDialogBox.setContentView(R.layout.item_receive_sms_dialouge_box);
            receiveSmsDialogBox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            receiveSmsDialogBox.getWindow().setGravity(Gravity.CENTER);
            receiveSmsDialogBox.show();
            TextView receiveSms=(TextView)receiveSmsDialogBox.findViewById(R.id.act_cashier_sms_tv);
            TextView receiveTotalAmount=(TextView)receiveSmsDialogBox.findViewById(R.id.act_cashier_sms_total_ammount_tv);
            EditText receiveCode=(EditText) receiveSmsDialogBox.findViewById(R.id.act_cashier_sms_et);
            Button acceptBtn=(Button)receiveSmsDialogBox.findViewById(R.id.act_cashier_sms_accept_btn);

            acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void sendClosingBalanceFunction() {

        Intent intent=new Intent(getApplicationContext(),CashierMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_NO_HISTORY);
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage("9490967421" , null, "Get balance "+mClosingBalance.getText().toString()+" Rs.", pi,null);
        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                Toast.LENGTH_LONG).show();


    }


}
