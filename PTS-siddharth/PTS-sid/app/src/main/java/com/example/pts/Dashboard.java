package com.example.pts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class Dashboard extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    Button signoutbutton, reviewatutor, categories,becomeatutor,hireatutor;
    ImageButton imageButton7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        firebaseAuth = FirebaseAuth.getInstance();
        signoutbutton = findViewById(R.id.signout);
        reviewatutor = findViewById(R.id.reviewatutor);
        categories = findViewById(R.id.categories);
        becomeatutor = findViewById(R.id.becomeatutor);
        hireatutor = findViewById(R.id.hireatutor);
        imageButton7 = findViewById(R.id.imageButton7);

        becomeatutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, BecomeATutor1.class);
                intent.putExtra("flags","become");
                startActivity(intent);
                finish();
            }
        });
        signoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent = new Intent(Dashboard.this, Login.class);
                startActivity(intent);
                finish();
                Toast.makeText(Dashboard.this, "Successful logout", Toast.LENGTH_SHORT).show();
            }
        });
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, TutoringCategoriesForTutors.class);
                startActivity(intent);
                finish();
            }
        });
        hireatutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, BecomeATutor1.class);
                intent.putExtra("flags","hire");
                startActivity(intent);
                finish();
            }
        });
    }
}