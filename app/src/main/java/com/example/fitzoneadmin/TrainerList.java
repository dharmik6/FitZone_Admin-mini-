package com.example.fitzoneadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class TrainerList extends AppCompatActivity {

    RecyclerView trainer_list ;
    RecyclerView.Adapter adaptor;

    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_list);

        trainer_list = findViewById(R.id.trainer_recyclerView);
        trainer_list.setHasFixedSize(true);
        trainer_list.setLayoutManager(new LinearLayoutManager(this));

        List<ListItem> trainerArrayList = new ArrayList<>();
        trainerArrayList.add(new ListItem("dharmik", "kacha", R.drawable.my));
        trainerArrayList.add(new ListItem("dharmik", "kacha", R.drawable.my));

        // Add more items as needed

        adapter = new MyAdapter(this, trainerArrayList);
        trainer_list.setAdapter(adapter);



    }



}