package com.example.fitzoneadmin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

public class UserList extends AppCompatActivity {

    DrawerLayout drawerLayout ;
    NavigationView navigationView;

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private List<UserItem> userItems = new ArrayList<>();
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.user_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(this, userItems);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");

        adapter = new UserAdapter(getApplicationContext(), userItems);
        recyclerView.setAdapter(adapter);

        setDatabaseListener();


        //***************************************************
        //navigation bar
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationview);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.user) {
                    redirectActivity(UserList.this, UserList.class);
                } else if (id == R.id.trainer) {
                    redirectActivity(UserList.this, TrainerList.class);
                } else if (id == R.id.workout) {
                    redirectActivity(UserList.this, WorkoutList.class);
                } else if (id == R.id.diet) {
                    redirectActivity(UserList.this, DietList.class);
                }  else if (id == R.id.logout)
                {
                    SharedPreferences pref = getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("flag", false);
                    editor.apply();

                    // After logging out, navigate to the LoginActivity
                    Intent intent = new Intent(UserList.this, AdminLogin.class);
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
                userItems.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String userName = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String age = dataSnapshot.child("age").getValue(String.class);
//                    Long ageLong = dataSnapshot.child("age").getValue(Long.class);
                    Long heightLong = dataSnapshot.child("height").getValue(Long.class);
                    Long weightLong = dataSnapshot.child("weight").getValue(Long.class);
                    String gender = dataSnapshot.child("gender").getValue(String.class);
                    String phone = dataSnapshot.child("Number").getValue(String.class);
                    String uImage = dataSnapshot.child("img").getValue(String.class);

                    // Convert Long values to String
//                    String age = String.valueOf("age");
                    String height = String.valueOf(heightLong);
                    String weight = String.valueOf(weightLong);

                    if (userName != null) {
                        UserItem userItem = new UserItem();
                        userItem.setUserName(userName);
                        userItem.setAge(age);
                        userItem.setGender(gender);
                        userItem.setEmail(email);
                        userItem.setNumber(phone);
                        userItem.setHiegth(height);
                        userItem.setWiegth(weight);
                        userItem.setUserImageResourceId(uImage);

                        userItems.add(userItem);

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