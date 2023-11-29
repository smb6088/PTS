package com.example.pts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BecomeATutor2 extends AppCompatActivity {
    EditText editTextPrice;
    EditText editTextLocation;
    EditText editTextBio;
    boolean agreedToTerms;
    CheckBox checkBox;
    Button buttonSubmit;
    DatabaseReference dataRef;
    TextView charCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_atutor2);

        Toolbar top = findViewById(R.id.xml_top);
        setSupportActionBar(top);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Select Category");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);

        agreedToTerms = false;

        Intent r_intent = getIntent();
        String temp = r_intent.getStringExtra("KEY_VALUE");


        editTextPrice = findViewById(R.id.editTextPrice);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextBio = findViewById(R.id.editTextBio);

        checkBox = findViewById(R.id.checkBox);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        charCount = findViewById(R.id.charCount);

        editTextBio.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String charCounter = String.valueOf(s.length());
                charCounter += " / 160";
                charCount.setText(charCounter);

                if(s.length() >= 160)
                {
                    editTextBio.setError("Character limit reached!");
                    editTextBio.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

        buttonSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String price = editTextPrice.getText().toString();
                String location = editTextLocation.getText().toString();
                String bio = editTextBio.getText().toString();
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = auth.getCurrentUser();
                String tutorid = firebaseUser.getUid().toString();
                String rating = "0";
                Intent r_intent = getIntent();
                String temp = r_intent.getStringExtra("KEY_VALUE");
                if(TextUtils.isEmpty(price))
                {
                    editTextPrice.setError("You did not set a price");
                    editTextPrice.requestFocus();
                }
                else if(TextUtils.isEmpty(location))
                {
                    editTextLocation.setError("You did not set a location");
                    editTextPrice.requestFocus();
                }
                else if(TextUtils.isEmpty(bio))
                {
                    editTextBio.setError("You did not write a biography");
                    editTextBio.requestFocus();
                }
                else if(!agreedToTerms)
                {
                    Toast.makeText(BecomeATutor2.this, "You must agree to the terms to proceed", Toast.LENGTH_SHORT).show();
                }
                else if(agreedToTerms)
                {
                    addtutor(tutorid,price,location, rating, bio, temp);
                    
                    /*
                    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    dataRef = FirebaseDatabase.getInstance().getReference().child("Tutors");
                    dataRef = dataRef.child(userID);
                    Map<String, Object> tutorDetails = new HashMap<String, Object>();

                    tutorDetails.put("Price", price);
                    tutorDetails.put("Location", location);
                    tutorDetails.put("Bio", bio);
                    tutorDetails.put("Category", selectedCategory);

                    dataRef.child("Tutor Details").push().updateChildren(tutorDetails);
                    */
                }



            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            Intent intent = new Intent(BecomeATutor2.this, BecomeATutor1.class);
            startActivity(intent);
            finish();
            return true;
        }

        else
        {
            return super.onOptionsItemSelected(item);
        }
    }


    public void addtutor( String tutorid,String price, String location, String rating, String bio, String subject)
    {

        //UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(FirstName).build();
        //firebaseUser.updateProfile(profileChangeRequest);
        tutorDetails tutordetails = new tutorDetails(tutorid, price,location,rating,bio, subject);
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Tutor Details");
        String id = referenceProfile.push().getKey();
        referenceProfile.child("ListofTutors").child(id).setValue(tutordetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(BecomeATutor2.this, "You became a tutor", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BecomeATutor2.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
}