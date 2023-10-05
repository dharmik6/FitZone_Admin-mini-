package com.example.fitzoneadmin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class DietList extends AppCompatActivity {
    TextView add_diet;
    private RecyclerView recyclerView;
    private DietAdapter adapter;
    private List<DietItem> dietItems = new ArrayList<>();
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_list);

        //*****************************
        // add diet
        add_diet = findViewById(R.id.add_diet);

        add_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DietList.this, DietAdd.class));
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

        recyclerView = findViewById(R.id.diet_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DietAdapter(this, dietItems);
        recyclerView.setAdapter(adapter);

        // Call a method to retrieve data from Firebase
        retrieveDataFromFirebase();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.user) {
                    redirectActivity(DietList.this, UserList.class);
                } else if (id == R.id.trainer) {
                    redirectActivity(DietList.this, TrainerList.class);
                } else if (id == R.id.workout) {
                    redirectActivity(DietList.this, WorkoutList.class);
                } else if (id == R.id.diet) {
                    // Do nothing, already in DietList activity
                } else if (id == R.id.logout) {
                    SharedPreferences pref = getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("flag", false);
                    editor.apply();

                    // After logging out, navigate to the LoginActivity
                    Intent intent = new Intent(DietList.this, AdminLogin.class);
                    startActivity(intent);
                    finish(); // Close the current activity
                }

                closeDrawer(drawerLayout);

                return true;
            }
        });
    }

    private void retrieveDataFromFirebase() {
        // Get reference to your Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("diet_items");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dietItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DietItem dietItem = snapshot.getValue(DietItem.class);
                    dietItems.add(dietItem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
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
