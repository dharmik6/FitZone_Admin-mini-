package com.example.fitzoneadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DietList extends AppCompatActivity {
    TextView add_diet ;
    RecyclerView diet_list;
    MyAdapter2 adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_list);

        add_diet = findViewById(R.id.add_diet);
        add_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(DietList.this,DietAdd.class);
                startActivity(user);
            }
        });

        diet_list = findViewById(R.id.diet_recyclerView);
        diet_list.setHasFixedSize(true);
        diet_list.setLayoutManager(new LinearLayoutManager(this));

        List<ListItem2> dietArrayList = new ArrayList<>();
        dietArrayList.add(new ListItem2("dharmik", R.drawable.my));

        // Add more items as needed
        dietArrayList.add(new ListItem2("Another Item",  R.drawable.my ));

        adapter = new MyAdapter2(this, dietArrayList);
        diet_list.setAdapter(adapter);

        //**********************************
        //back page button
        ImageView back_page = findViewById(R.id.btn_next_page);
        back_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}