package com.example.fitzoneadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DietList extends AppCompatActivity {
    RecyclerView diet_list;
    MyAdapter2 adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_list);


        diet_list = findViewById(R.id.diet_recyclerView);
        diet_list.setHasFixedSize(true);
        diet_list.setLayoutManager(new LinearLayoutManager(this));

        List<ListItem2> dietArrayList = new ArrayList<>();
        dietArrayList.add(new ListItem2("dharmik", R.drawable.my));

        // Add more items as needed
        dietArrayList.add(new ListItem2("Another Item",  R.drawable.my ));

        adapter = new MyAdapter2(this, dietArrayList);
        diet_list.setAdapter(adapter);
    }
}