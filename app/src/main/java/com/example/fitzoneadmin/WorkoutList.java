package com.example.fitzoneadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WorkoutList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WorkoutAdapter adapter;
    private List<WorkoutItem> workoutItems = new ArrayList<>();

    TextView add_work ;
    DrawerLayout drawerLayout ;
    NavigationView navigationView;
    DatabaseReference databaseReference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);


        recyclerView = findViewById(R.id.work_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WorkoutAdapter(this, workoutItems);
        recyclerView.setAdapter(adapter);

        // Initialize Firebase
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("workouts");

        setDatabaseListener();

        // Initialize UI components
        add_work = findViewById(R.id.add_workout);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationview);
        ImageView menu = findViewById(R.id.menu);

        // Handle click events
        add_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iworkadd = new Intent(WorkoutList.this, WorkoutAdd.class);
                startActivity(iworkadd);
            }
        });

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
                    redirectActivity(WorkoutList.this, UserList.class);
                } else if (id == R.id.trainer) {
                    redirectActivity(WorkoutList.this, TrainerList.class);
                } else if (id == R.id.workout) {
                    redirectActivity(WorkoutList.this, WorkoutList.class);
                } else if (id == R.id.diet) {
                    redirectActivity(WorkoutList.this, DietList.class);
                } else {
                    Toast.makeText(WorkoutList.this, "profile", Toast.LENGTH_SHORT).show();
                }

                closeDrawer(drawerLayout);
                return true;
            }
        });
    }

    private void setDatabaseListener() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                workoutItems.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    WorkoutItem workoutItem = dataSnapshot.getValue(WorkoutItem.class);
                    if (workoutItem != null) {
                        workoutItems.add(workoutItem);
                    }
                }
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
                // Add debug logs to verify data retrieval
                Log.d("FirebaseData", "WorkoutItems: " + workoutItems.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
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

