package com.example.pts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Userprofile extends AppCompatActivity {

    TextView Fname,Lname, Email,Phone,Teaching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        Toolbar top = findViewById(R.id.xml_top);
        setSupportActionBar(top);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Profile Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);
        Fname = findViewById(R.id.fname);
        Lname = findViewById(R.id.lastname);
        Email = findViewById(R.id.email);
        Phone = findViewById(R.id.phone);
        Teaching = findViewById(R.id.teaching);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
        referenceProfile.child(firebaseUser.getUid()).child("FirstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String fname = snapshot.getValue(String.class);
                    Fname.setText(fname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        referenceProfile.child(firebaseUser.getUid()).child("LastName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String fname = snapshot.getValue(String.class);
                    Lname.setText(fname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        referenceProfile.child(firebaseUser.getUid()).child("EmailAddress").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String fname = snapshot.getValue(String.class);
                    Email.setText(fname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        referenceProfile.child(firebaseUser.getUid()).child("Phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String fname = snapshot.getValue(String.class);
                    Phone.setText(fname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(Userprofile.this, Dashboard.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }
}