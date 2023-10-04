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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);
        //********************************************
        // add new item
        add_work = findViewById(R.id.add_workout);

        add_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iworkadd = new Intent(WorkoutList.this,WorkoutAdd.class);
                startActivity(iworkadd);
            }
        });


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

        recyclerView = findViewById(R.id.work_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WorkoutAdapter(this, workoutItems);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("workouts");

        adapter = new WorkoutAdapter(getApplicationContext(), workoutItems);
        recyclerView.setAdapter(adapter);

        setDatabaseListener();
    }

    private void setDatabaseListener() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                workoutItems.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String workoutName = dataSnapshot.child("workoutName").getValue(String.class);
                    String workoutFocusArea = dataSnapshot.child("workoutFocusArea").getValue(String.class);
                    String workoutImageResourceId = dataSnapshot.child("workoutImageResourceId").getValue(String.class); // Change to "imageUrl"

                    if (workoutName != null && workoutFocusArea != null&& workoutImageResourceId != null) {
                        WorkoutItem workoutItem = new WorkoutItem(workoutName, workoutFocusArea,workoutImageResourceId);
                        workoutItems.add(workoutItem);
                    }
                }
                adapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
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

