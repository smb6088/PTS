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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TutoringCategoriesForTutors extends AppCompatActivity implements AddCategoryDialog.DialogListener {

    FloatingActionButton floatingEdit;
    private ListView listviewCategories;
    ArrayList<String> list;
    ArrayAdapter arrAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoring_categories_for_tutors);

        Toolbar top = findViewById(R.id.xml_top2);
        setSupportActionBar(top);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Categories");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);


        listviewCategories = findViewById(R.id.listCategories);
        list = new ArrayList<>();
        arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listviewCategories.setAdapter(arrAdapter);

        floatingEdit = findViewById(R.id.floatingEdit);



        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference().child("Tutoring Categories");
        dataRef.addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for(DataSnapshot snap_shot : snapshot.getChildren())
                {
                    if(list.contains(snap_shot.getKey().toString())==false)
                    {
                        list.add(snap_shot.getKey().toString());
                    }
                }
                arrAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

        floatingEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TutoringCategoriesForTutors.this, "Fab clicked", Toast.LENGTH_SHORT).show();
                openDialog();
            }
        });
    }

    public void openDialog()
    {
        AddCategoryDialog addCategoryDialog = new AddCategoryDialog();
        addCategoryDialog.show(getSupportFragmentManager(),"add category");

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            Intent intent = new Intent(TutoringCategoriesForTutors.this, Dashboard.class);
            startActivity(intent);
            finish();
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void applyTexts(String categoryName) {
        Toast.makeText(TutoringCategoriesForTutors.this,categoryName, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(TutoringCategoriesForTutors.this, BecomeATutor2.class);
        intent.putExtra("KEY_VALUE", categoryName);
        intent.putExtra("ACTIVITY", "Categories");
        startActivity(intent);
        finish();

    }
}