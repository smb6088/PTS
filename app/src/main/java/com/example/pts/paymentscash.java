package com.example.pts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class paymentscash extends AppCompatActivity {

    CheckBox checkBox;
    Button pay;
    Boolean agreedToTerms;
    String tutorID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentscash);
        Intent intent = getIntent();
        tutorID = intent.getStringExtra("TUTORID");
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



                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Registered Users/"+uid);
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //ArrayList<String> prevHiredTutors;
                            for(DataSnapshot child: snapshot.getChildren())
                            {
                                if(child.getKey().toString().equals("prevHiredTutors"))
                                {
                                    Map<String,Object> prevHiredTutors = new HashMap<String,Object>();
                                    prevHiredTutors.put(tutorID,"");
                                    DatabaseReference updatingRef = FirebaseDatabase.getInstance().getReference("Registered Users/"+uid);
                                    updatingRef.child("prevHiredTutors").updateChildren(prevHiredTutors);
                                    Map<String,Object> teachingStudents = new HashMap<String,Object>();
                                    teachingStudents.put(uid,"");
                                    DatabaseReference updatingRef2 = FirebaseDatabase.getInstance().getReference("Registered Users/" + tutorID);
                                    updatingRef2.child("teachingStudents").updateChildren(teachingStudents);


                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

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