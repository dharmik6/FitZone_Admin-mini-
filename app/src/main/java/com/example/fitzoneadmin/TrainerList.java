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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class TrainerList extends AppCompatActivity {
TextView add_trainer ;
    private RecyclerView recyclerView;
    private TrainerAdapter adapter;
    private List<TrainerItem> trainerItems = new ArrayList<>();
    DatabaseReference databaseReference;
    DrawerLayout drawerLayout ;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_list);


        recyclerView = findViewById(R.id.trainer_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TrainerAdapter(this, trainerItems);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("trainer");

        adapter = new TrainerAdapter(getApplicationContext(), trainerItems);
        recyclerView.setAdapter(adapter);

        setDatabaseListener();

        //***************************************************
        //navigation bar
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationview);
        ImageView menu = findViewById(R.id.menu);
        add_trainer = findViewById(R.id.add_trainer);
        add_trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(TrainerList.this,TrainerAdd.class);
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
                    redirectActivity(TrainerList.this, UserList.class);
                } else if (id == R.id.trainer) {
                    redirectActivity(TrainerList.this, TrainerList.class);
                } else if (id == R.id.workout) {
                    redirectActivity(TrainerList.this, WorkoutList.class);
                } else if (id == R.id.diet) {
                    redirectActivity(TrainerList.this, DietList.class);
                }  else if (id == R.id.logout)
                {
                    SharedPreferences pref = getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("flag", false);
                    editor.apply();

                    // After logging out, navigate to the LoginActivity
                    Intent intent = new Intent(TrainerList.this, AdminLogin.class);
                    startActivity(intent);
                    finish(); // Close th
                }

                closeDrawer(drawerLayout);

                return true;
            }
        });
    }
    private void setDatabaseListener() {
        ImageView menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                trainerItems.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


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