package com.example.pts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    ImageButton ContactBtn;
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
    Button ButtonPay;
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
        ContactBtn = findViewById(R.id.contactButton);


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



        ContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        ButtonPay = findViewById(R.id.paybtn);
        ButtonPay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HireaTutor3.this,Payments.class);
                intent.putExtra("TUTORID",TutorID);
                startActivity(intent);

            }
        });




    }
    public void openDialog(){
        ContactDialoog exampleDialog = new ContactDialoog();
        exampleDialog.setText(Phone,Email);
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }
}