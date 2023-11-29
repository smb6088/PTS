package com.example.pts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Tutorprofile extends AppCompatActivity {

    TextView Fname, Lname, Location, Bio, Price, Servicefee, Tutorfee;
    Button btnpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorprofile);
        Fname = findViewById(R.id.firstname);
        Lname = findViewById(R.id.lastname);
        Location = findViewById(R.id.location);
        Bio = findViewById(R.id.bio);
        Price = findViewById(R.id.price);
        Servicefee = findViewById(R.id.servicefee);
        Tutorfee = findViewById(R.id.tutorfee);
        btnpay = findViewById(R.id.button);
    }
}