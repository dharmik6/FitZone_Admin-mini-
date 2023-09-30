package com.example.fitzoneadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class TrainerList extends AppCompatActivity {

    RecyclerView trainer_list ;
    RecyclerView.Adapter adaptor;

    MyAdapter adapter;
    DrawerLayout drawerLayout ;
    NavigationView navigationView;
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


        //***************************************************
        //navigation bar
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationview);
        ImageView menu = findViewById(R.id.menu);



        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.user) {
                    redirectActivity(TrainerList.this, UserList.class);
                } else if (id == R.id.trainer) {
                    redirectActivity(TrainerList.this, TrainerList.class);
                } else if (id == R.id.workout) {
                    redirectActivity(TrainerList.this, WorkoutList.class);
                } else if (id == R.id.diet) {
                    redirectActivity(TrainerList.this, DietList.class);
                } else {
                    Toast.makeText(TrainerList.this, "profile", Toast.LENGTH_SHORT).show();
                }

                closeDrawer(drawerLayout);

                return true;
            }
        });
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

}