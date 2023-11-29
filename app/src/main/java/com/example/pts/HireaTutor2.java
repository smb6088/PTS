package com.example.pts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HireaTutor2 extends AppCompatActivity {

    //ArrayList<tutorsearch_model> tutorsearchModels = new ArrayList<String>();
    //ArrayAdapter arrAdapter;
//    String[] tutorKey;
//    String[] tutorName;
    String[] names;
    String[] rating;
    String[] location;
    Button Pay;
    String selectedCategory;

    ArrayAdapter arrAdapter;

    ListView tutorlist;
    RecyclerView recyclerView;
    ArrayList<String> list;
    DatabaseReference databaseReference;
    TP_recyclerviewadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirea_tutor2);
        Intent r_intent = getIntent();
        Pay = findViewById(R.id.payment);
        tutorlist = findViewById(R.id.listtutor);
        list = new ArrayList<>();
        arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,list);
        tutorlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        tutorlist.setAdapter(arrAdapter);

        selectedCategory = r_intent.getStringExtra("subject");

        databaseReference = FirebaseDatabase.getInstance().getReference("ListofTutor");
        String id = databaseReference.getKey();
//        list = new ArrayList<>();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new TP_recyclerviewadapter(this, list);
        ArrayList<String> tutorData = new ArrayList<>();
        System.out.println(selectedCategory);

//        .orderByChild("subject").equalTo(selectedCategory);
        databaseReference.orderByChild("subject").equalTo(selectedCategory).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                       String tutorKey = dataSnapshot.getKey();
                       System.out.println(tutorKey);
                       String tutorName = dataSnapshot.child("location").getValue(String.class);
                       System.out.println(tutorName);

                    String tutorInfo = "Key: " + tutorKey + ", Name: " + tutorName;
                    tutorData.add(tutorInfo);

                    System.out.println(tutorData);

//                    list.add(tutorsearchModel);
                }
//                adapter.notifyDataSetChanged();
//                TP_recyclerviewadapter temp = new TP_recyclerviewadapter(HireaTutor2.this,list);
                //recyclerView.setLayoutManager(new LinearLayoutManager(HireaTutor2.this));
//                recyclerView.setAdapter(adapter);
//                adapter = new TP_recyclerviewadapter(HireaTutor2.this, list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HireaTutor2.this,Payments.class);
//                String name = intent.putExtra("nameid", )
                startActivity(intent);
                finish();
            }
        });
    }
}