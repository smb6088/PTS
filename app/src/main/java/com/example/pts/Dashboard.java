package com.example.pts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Dashboard extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    Button signoutbutton, reviewatutor, categories,becomeatutor,hireatutor;
    TextView fname;
    ImageButton ProfileButton;
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
        ProfileButton = findViewById(R.id.profilebtn);
        fname = findViewById(R.id.name);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
        referenceProfile.child(firebaseUser.getUid()).child("FirstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = snapshot.getValue(String.class);
                    fname.setText(name);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        becomeatutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, BecomeATutor1.class);
                intent.putExtra("flags","become");
                startActivity(intent);
                //finish();
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
                //finish();
            }
        });
        hireatutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, BecomeATutor1.class);
                intent.putExtra("flags","hire");
                startActivity(intent);
                //finish();
            }
        });
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Userprofile.class);
                startActivity(intent);
                //finish();
            }
        });
        reviewatutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,BeforeReview.class);
                startActivity(intent);

            }
        });
    }
}