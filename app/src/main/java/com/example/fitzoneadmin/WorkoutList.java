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

public class WorkoutList extends AppCompatActivity {
    RecyclerView work_list;
    MyAdapter2 adapter;

    TextView add_work ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        add_work = findViewById(R.id.add_workout);
        add_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(WorkoutList.this,WorkoutAdd.class);
                startActivity(user);
            }
        });

        work_list = findViewById(R.id.work_recyclerView);
        work_list.setHasFixedSize(true);
        work_list.setLayoutManager(new LinearLayoutManager(this));


        List<ListItem2> workArrayList = new ArrayList<>();
        workArrayList.add(new ListItem2("dharmik", R.drawable.my));

        // Add more items as needed
        workArrayList.add(new ListItem2("Another Item",  R.drawable.my ));

        adapter = new MyAdapter2(this, workArrayList);
        work_list.setAdapter(adapter);

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
