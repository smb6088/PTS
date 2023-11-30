package com.example.pts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HireaTutor3 extends AppCompatActivity {

    String Fullname;
    String Email;
    String Phone;
    String Bio;
    String Price;
    String Category;
    String Location;
    String Rating;
    String TutorID;

    TextView bio;
    TextView fullname;
    TextView bioLabel;
    TextView location;
    TextView email;
    TextView rating;
    TextView appGets;
    TextView tutorGets;
    TextView totalPrice;
    TextView teachingLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirea_tutor3);

        Intent intent = getIntent();
        TutorID = intent.getStringExtra("TUTORID");
        Fullname = intent.getStringExtra("FULL_NAME");
        Email = intent.getStringExtra("EMAIL");
        Phone = intent.getStringExtra("PHONE");
        Rating = intent.getStringExtra("RATING");
        Location = intent.getStringExtra("LOCATION");
        Bio = intent.getStringExtra("BIO");
        Price = intent.getStringExtra("PRICE");
        Category = intent.getStringExtra("CATEGORY");
        teachingLabel =findViewById(R.id.teachingLabel);

        bio = findViewById(R.id.bio);
        bioLabel = findViewById(R.id.bioLabel);
        fullname = findViewById(R.id.fullname);
        location = findViewById(R.id.location);
        rating = findViewById(R.id.rating);
        email = findViewById(R.id.clickForContactLabel);
        totalPrice = findViewById(R.id.totalPrice);
        appGets = findViewById(R.id.appGets);
        tutorGets = findViewById(R.id.tutorGets);

        bio.setText(Bio);
        fullname.setText(Fullname);
        //email.setText(Email);
        rating.setText(Rating);
        location.setText(Location);
        teachingLabel.setText("Teaching in "+Category);
        totalPrice.setText(Price);
        tutorGets.setText(String.valueOf(Integer.parseInt(Price)*0.8));
        appGets.setText(String.valueOf(Integer.parseInt(Price)*0.2));


    }
}