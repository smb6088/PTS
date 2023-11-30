package com.example.pts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Paymentscredit extends AppCompatActivity {

    EditText EditCardNo, EditExpiryDate, EditCvv, EditFullName;
    Button Pay;

    CheckBox checkBox;
    Boolean agreedToTerms;

    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentscredit);
        EditCardNo = findViewById(R.id.editcardno);
        EditExpiryDate = findViewById(R.id.editexdate);
        checkBox = findViewById(R.id.checkBox);
        EditCvv = findViewById(R.id.editCVV);
        EditFullName = findViewById(R.id.editname);

        Pay = findViewById(R.id.paybtn);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    agreedToTerms = true;
                }
                else
                {
                    agreedToTerms = false;

                }
            }
        });

        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtcard = EditCardNo.getText().toString();
                String txtexdate = EditExpiryDate.getText().toString();
                String txtcvv = EditCvv.getText().toString();
                String txtfullname = EditFullName.getText().toString();

                if(TextUtils.isEmpty(txtcard) || txtcard.length()<16) {
                    Toast.makeText(Paymentscredit.this, "Please enter valid card no.", Toast.LENGTH_LONG).show();
                    EditCardNo.setError("valid no. required");
                    EditCardNo.requestFocus();
                } else if(TextUtils.isEmpty(txtexdate)){
                    Toast.makeText(Paymentscredit.this, "Please enter your card expiry date.", Toast.LENGTH_LONG).show();
                    EditExpiryDate.setError("expiry date can not be empty");
                    EditExpiryDate.requestFocus();
                }else if(TextUtils.isEmpty(txtcvv) || txtcvv.length() != 3){
                    Toast.makeText(Paymentscredit.this, "Please enter your card Cvv", Toast.LENGTH_LONG).show();
                    EditCvv.setError("Valid cvv is required");
                    EditCvv.requestFocus();
                }else if(TextUtils.isEmpty(txtfullname)){
                    Toast.makeText(Paymentscredit.this, "Please enter your full name.", Toast.LENGTH_LONG).show();
                    EditFullName.setError("full name is required.");
                    EditFullName.requestFocus();
                } else if(!agreedToTerms)
                {
                    Toast.makeText(Paymentscredit.this, "You must agree to the terms to proceed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Paymentscredit.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Paymentscredit.this, Dashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                            | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}