package com.example.pts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class paymentscash extends AppCompatActivity {

    CheckBox checkBox;
    Button pay;
    Boolean agreedToTerms;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentscash);
        checkBox = findViewById(R.id.checkBox);
        pay = findViewById(R.id.btnpay);

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

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!agreedToTerms)
                {
                    Toast.makeText(paymentscash.this, "You must agree to the terms to proceed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(paymentscash.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(paymentscash.this, Dashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                            | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}