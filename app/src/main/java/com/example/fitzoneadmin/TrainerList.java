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

    private RecyclerView recyclerView;
    private TrainerAdapter adapter;
    private List<TrainerItem> trainerItems = new ArrayList<>();

    DrawerLayout drawerLayout ;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_list);

        trainerItems.add(new TrainerItem("Trainer 1", R.drawable.baseline_image_24));
        trainerItems.add(new TrainerItem("Trainer 2", R.drawable.baseline_image_24));
        trainerItems.add(new TrainerItem("Trainer 3", R.drawable.baseline_image_24));

        recyclerView = findViewById(R.id.trainer_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new TrainerAdapter(getApplicationContext(), trainerItems);
        recyclerView.setAdapter(adapter);

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