package com.example.pts;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HireaTutor2 extends AppCompatActivity {

    String selectedCategory;
    String Name;
    String Rating;
    String Location;
    ArrayList<TutorSearchModel> tutorSearchModels = new ArrayList<>();
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
                                tutorSearchModels.add(new TutorSearchModel(user_list.get(1)+user_list.get(2),user_list.get(7),user_list.get(3)));

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

        rv_tutor_adapter adapter = new rv_tutor_adapter(this, tutorSearchModels);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));




        //String Names[] = {}
        //String Ratings[] =

    }
}