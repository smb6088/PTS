package com.example.pts;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HireaTutor2 extends AppCompatActivity implements rv_interface {

    String selectedCategory;
    String Name;
    String Rating;
    String Location;


    ArrayList<TutorSearchModel> tutorSearchModels = new ArrayList<>();
    ArrayList<String> TutorID = new ArrayList<>();
    ArrayList<String> Prices = new ArrayList<>();
    ArrayList<String> Bios = new ArrayList<>();
    ArrayList<String> Locations = new ArrayList<>();
    ArrayList<String> Emails = new ArrayList<>();
    ArrayList<String> Phones = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirea_tutor2);

        Intent intent = getIntent();
        selectedCategory = intent.getStringExtra("KEY_VALUE");
        Toast.makeText(HireaTutor2.this, "Hiring for " + selectedCategory,Toast.LENGTH_SHORT).show();


        RecyclerView rv = findViewById(R.id.tutorrecycler);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Tutor Details/ListofTutors");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot childSnap : snapshot.getChildren())
                {
                    ArrayList<String> list = new ArrayList<>();
                    for(DataSnapshot childSnap2 : childSnap.getChildren())
                    {
                        list.add(childSnap2.getValue().toString());
                    }

                    if(list.get(2).equals(selectedCategory))
                    {

                        DatabaseReference reg_ref = FirebaseDatabase.getInstance().getReference("Registered Users/"+list.get(3));
                        reg_ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot usersnapshot)
                            {
                                ArrayList<String> user_list = new ArrayList<>();
                                for(DataSnapshot userchildsnap : usersnapshot.getChildren())
                                {
                                    user_list.add(userchildsnap.getValue().toString());
                                }
                                tutorSearchModels.add(new TutorSearchModel(user_list.get(1)+" "+user_list.get(2),user_list.get(7),user_list.get(3)));
                                TutorID.add(list.get(3));
                                Prices.add(list.get(1));
                                Bios.add(list.get(0));
                                Emails.add(user_list.get(0));
                                Phones.add(user_list.get(6));

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        //tutorSearchModels.add(new TutorSearchModel("hello","hello","hello"));

                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rv_tutor_adapter adapter = new rv_tutor_adapter(this, tutorSearchModels, this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));




        //String Names[] = {}
        //String Ratings[] =

    }

    @Override
    public void onClick(int position)
    {
        Intent intent = new Intent(HireaTutor2.this, HireaTutor3.class);
        intent.putExtra("FULL_NAME",tutorSearchModels.get(position).getName());
        intent.putExtra("RATING",tutorSearchModels.get(position).getRating());
        intent.putExtra("LOCATION",tutorSearchModels.get(position).getLocation());
        intent.putExtra("BIO",Bios.get(position));
        intent.putExtra("PRICE",Prices.get(position));
        intent.putExtra("CATEGORY", selectedCategory);
        intent.putExtra("PHONE",Phones.get(position));
        intent.putExtra("EMAIL",Emails.get(position));
        intent.putExtra("TUTORID",TutorID.get(position));

        startActivity(intent);
        finish();



    }
}