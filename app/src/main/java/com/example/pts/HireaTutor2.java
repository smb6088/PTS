package com.example.pts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HireaTutor2 extends AppCompatActivity {

    //ArrayList<tutorsearch_model> tutorsearchModels = new ArrayList<String>();
    //ArrayAdapter arrAdapter;
    Button Pay;
    String selectedCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirea_tutor2);
        Intent r_intent = getIntent();
        Pay = findViewById(R.id.payment);
        selectedCategory = r_intent.getStringExtra("subject");

        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HireaTutor2.this,Payments.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private  void setuptutorsmodels(){
        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference().child("Tutor Details");
        dataRef.addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                for(DataSnapshot snap_shot : snapshot.getChildren())
                {
                    System.out.println(snap_shot.getValue().toString());
                    //String[] bio = new String[]{snap_shot.child("bio").getValue().toString()};
                }
                //arrAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }
}