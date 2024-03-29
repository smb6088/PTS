package com.example.pts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BeforeReview extends AppCompatActivity {

    private ListView listviewCategories;
    ArrayList<String> list;
    ArrayAdapter arrAdapter;
    Button buttonNext;
    //ImageButton imageButtonNext;
    String selectedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_review);

        Toolbar top = findViewById(R.id.xml_top);
        setSupportActionBar(top);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);


        listviewCategories = findViewById(R.id.listviewTutors);
        list = new ArrayList<>();
        arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list);
        listviewCategories.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listviewCategories.setAdapter(arrAdapter);




        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("Registered Users/"+uid+"/prevHiredTutors");
        dataRef.addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot snap_shot : snapshot.getChildren())
                {

                    String tutorID = snap_shot.getKey().toString();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Registered Users/"+tutorID);
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dsnapshot) {
                            String name="";
                            for(DataSnapshot child: dsnapshot.getChildren())
                            {
                                if(child.getKey().toString().equals("FirstName"))
                                {
                                    name += child.getValue().toString();
                                }
                                if(child.getKey().toString().equals("LastName"))
                                {
                                    name += child.getValue().toString();
                                }
                                if(list.contains(name)==false)
                                {
                                    list.add(name);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                arrAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
        listviewCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String toast_mssg = "Selected ";
                selectedItem = (parent.getItemAtPosition(position)).toString();
                toast_mssg += selectedItem;
                toast_mssg += " as tutor to review";
                Toast.makeText(BeforeReview.this, toast_mssg, Toast.LENGTH_SHORT).show();

            }
        });
        /*
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s_intent = new Intent(BecomeATutor1.this, BecomeATutor2.class);
                s_intent.putExtra("KEY_VALUE",selectedItem);
                startActivity(s_intent);
                finish();
            }
        });
        */

        //buttonNext = findViewById(R.id.submitCategory);
        /*buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BecomeATutor1.this, "Category " +listviewCategories.getSelectedItem().toString()+" is selected",Toast.LENGTH_LONG).show();
                Intent s_intent = new Intent(BecomeATutor1.this, BecomeATutor2.class);
                s_intent.putExtra("KEY_VALUE",listviewCategories.getSelectedItem().toString());
                startActivity(s_intent);
                finish();
            }
        });*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            Intent intent = new Intent(BeforeReview.this, Dashboard.class);
            startActivity(intent);
            finish();
            return true;
        }
        else if(item.getItemId()==R.id.check)
        {



                Intent s_intent = new Intent(BeforeReview.this, Review.class);
                s_intent.putExtra("KEY_VALUE", selectedItem);
                startActivity(s_intent);
                //finish();
                return true;



        }
        else
        {
            return super.onOptionsItemSelected(item);
        }
    }
}