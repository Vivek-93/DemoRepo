package com.bitplay.restpos.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bitplay.restpos.R;
import com.bitplay.restpos.interfaces.register.IRegisterPresenter;
import com.bitplay.restpos.interfaces.register.IRegisterView;
import com.bitplay.restpos.interfaces.register.RegisterPresenterImpl;
import com.bitplay.restpos.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IRegisterView, AdapterView.OnItemSelectedListener {


    //  private final AppCompatActivity activity = RegisterActivity.this;

    public EditText regMobileNumber, regUserName, regEmail, regPass, regAddress, regFathers, regAadharno, regPan;
    public Spinner mSelectType;
    public ImageView regBackIv;
    public Button registerBtn;
    private IRegisterPresenter mIRegisterPresenter;

    List<String> typesData;

    String nameValue, passwordValue, phoneNumValue, emailValue, addressValue, fathersnameValue, aadharValue, panValue;
    private String selectRole;
    private String label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        regUserName = (EditText) findViewById(R.id.reg_act_name_et);
        regMobileNumber = (EditText) findViewById(R.id.reg_act_mobileno_et);
        regEmail = (EditText) findViewById(R.id.reg_act_email_et);
        regPass = (EditText) findViewById(R.id.reg_act_passwo_et);
        regAddress = (EditText) findViewById(R.id.reg_act_address_et);
        regFathers = (EditText) findViewById(R.id.reg_act_fathers_et);
        regAadharno = (EditText) findViewById(R.id.reg_act_aadharno_et);
        regPan = (EditText) findViewById(R.id.reg_act_pan_et);
        registerBtn = (Button) findViewById(R.id.register_act_sign_up_button);
        mSelectType = (Spinner) findViewById(R.id.reg_act_select_type);
        regBackIv = (ImageView) findViewById(R.id.reg_act_back_iv);
        registerBtn.setOnClickListener(this);
        regBackIv.setOnClickListener(this);


        initializeViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeViews();
    }

    private void initializeViews() {
        mIRegisterPresenter = new RegisterPresenterImpl(RegisterActivity.this);
        // mIRegisterPresenter = new RegisterPresenterImpl(RegisterActivity.this);
        mSelectType.setOnItemSelectedListener(this);
        setSpinnerData();


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
     //   label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
      /*  Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();*/

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    private void setSpinnerData() {
        typesData = new ArrayList<String>();
        typesData.add("Select a role");
        typesData.add("Captain");
        typesData.add("Cashier");
        typesData.add("Kitchen Display");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, typesData);
        mSelectType.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.register_act_sign_up_button:
                attemptregister();
                break;

            case R.id.reg_act_back_iv:
                onBackPressed();
                break;
        }
    }

    private void attemptregister() {


        //     mIRegisterPresenter.registerApiCall(regUserName.getText().toString(), mPasswordET.getText().toString() );
        gettingAllTheValues();
        getSpinnerData();
        if (regUserName.getText().toString().length() == 0) {
            Toast.makeText(this, "Name cant be blank", Toast.LENGTH_SHORT).show();
            regUserName.setError("Name cant be blank");

        }else if (regMobileNumber.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show();
            regMobileNumber.setError("Enter a valid number");

        }  else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(regEmail.getText().toString()).matches()) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
            regEmail.setError("Invalid Email");

        }  else if (regPass.getText().toString().length() == 0) {
            Toast.makeText(this, "Password cant be blank", Toast.LENGTH_SHORT).show();
            regPass.setError("Password cant be blank");

        }else if (regAddress.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter a address", Toast.LENGTH_SHORT).show();
            regAddress.setError("Enter a address");

        } else if (regFathers.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter your father's name", Toast.LENGTH_SHORT).show();
            regFathers.setError("Enter your father's name");

        } else if (regAadharno.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter Aadhar number", Toast.LENGTH_SHORT).show();
            regAadharno.setError("Enter Aadhar number");

        } else if (regPan.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter your Pan number", Toast.LENGTH_SHORT).show();
            regPan.setError("Enter your Pan number");
        } else {
            mIRegisterPresenter.registerApiCall(nameValue, phoneNumValue, emailValue, passwordValue, addressValue, fathersnameValue, aadharValue, panValue, selectRole);
            emptyInputEditText();

        }

    }


    private void getSpinnerData() {
        selectRole = mSelectType.getSelectedItem().toString();
    }

    private void emptyInputEditText() {

        regUserName.setText(null);
        regMobileNumber.setText(null);
        regEmail.setText(null);
        regPass.setText(null);
        regFathers.setText(null);
        regAddress.setText(null);
        regAadharno.setText(null);
        regPan.setText(null);

    }


    private void gettingAllTheValues() {
        try {
            nameValue = regUserName.getText().toString().trim();
            passwordValue = regPass.getText().toString().trim();
            phoneNumValue = regMobileNumber.getText().toString().trim();
            emailValue = regEmail.getText().toString().trim();
            addressValue = regAddress.getText().toString().trim();
            fathersnameValue = regFathers.getText().toString();
            aadharValue = regAadharno.getText().toString().trim();
            panValue = regPan.getText().toString().trim();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRegisterSuccess(int pid, String registerModel) {

        Utils.stopProgress(RegisterActivity.this);
      if (registerModel.equals(0)){
          Toast.makeText(this, "Email Id is already registered", Toast.LENGTH_SHORT).show();

      }else {
          Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
          startActivity(intent);
      }

    }

    @Override
    public void onRegisterError(int pid, String registerErrorModel) {

        Utils.stopProgress(RegisterActivity.this);
        Toast.makeText(this, "Register error", Toast.LENGTH_SHORT).show();

    }
}
