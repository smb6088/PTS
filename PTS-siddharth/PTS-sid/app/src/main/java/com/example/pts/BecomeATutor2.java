package com.example.pts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class BecomeATutor2 extends AppCompatActivity {
    String selectedCategory;
    EditText editTextPrice;
    EditText editTextLocation;
    EditText editTextBio;
    boolean agreedToTerms;
    CheckBox checkBox;
    Button buttonSubmit;
    DatabaseReference dataRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_atutor2);
        agreedToTerms = false;

        Intent r_intent = getIntent();
        selectedCategory = r_intent.getStringExtra("KEY_VALUE");

        editTextPrice = findViewById(R.id.editTextPrice);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextBio = findViewById(R.id.editTextBio);

        checkBox = findViewById(R.id.checkBox);
        buttonSubmit = findViewById(R.id.buttonSubmit);

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

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agreedToTerms) {
                    String price = editTextPrice.getText().toString();
                    String location = editTextLocation.getText().toString();
                    String bio = editTextBio.getText().toString();

                    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    dataRef = FirebaseDatabase.getInstance().getReference().child("Registered Users");
                    dataRef = dataRef.child(userID);
                    Map<String, Object> tutorDetails = new HashMap<String, Object>();

                    tutorDetails.put("Price", price);
                    tutorDetails.put("Location", location);
                    tutorDetails.put("Bio", bio);
                    tutorDetails.put("Category", selectedCategory);

                    dataRef.child("Tutor Details").push().updateChildren(tutorDetails);

                    Intent intent = new Intent(BecomeATutor2.this, Dashboard.class);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    Toast.makeText(BecomeATutor2.this, "You must agree to the terms to proceed", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}