package com.example.pts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
public class Payments extends AppCompatActivity {

    private ListView listviewCategories;
    ArrayList<String> list;

    String selectedItem;

    ArrayAdapter arrAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        Toolbar top;
        top = findViewById(R.id.xml_top);
        setSupportActionBar(top);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Payment Mode");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24);
        Intent intent = getIntent();


        listviewCategories = findViewById(R.id.listviewCategories);
        list = new ArrayList<>();
        list.add("Cash");
        list.add("Credit/Debit card");

        arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list);
        listviewCategories.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listviewCategories.setAdapter(arrAdapter);

        listviewCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String toast_mssg = "Selected ";
                selectedItem = (parent.getItemAtPosition(position)).toString();
                toast_mssg += selectedItem;
                toast_mssg += " as a payment mode";
                Toast.makeText(Payments.this, toast_mssg, Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(Payments.this, Dashboard.class);
            startActivity(intent);
            finish();
            return true;
        } else if (item.getItemId() == R.id.check) {
            if (selectedItem.equals("Cash")) {
                Intent s_intent = new Intent(Payments.this, paymentscash.class);
                s_intent.putExtra("mode", selectedItem);
                startActivity(s_intent);
                finish();
                return true;
            } else if (selectedItem.equals("Credit/Debit card")) {
                Intent s_intent = new Intent(Payments.this, Paymentscredit.class);
                s_intent.putExtra("mode", selectedItem);
                startActivity(s_intent);
                finish();
                return true;
            }
            return false;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }




}