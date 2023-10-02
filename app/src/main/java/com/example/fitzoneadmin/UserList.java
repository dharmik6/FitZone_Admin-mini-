package com.example.fitzoneadmin;

import android.app.Activity;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        userItems.add(new UserItem("User 1", R.drawable.round_menu_24));
        userItems.add(new UserItem("User 2", R.drawable.round_menu_24));
        userItems.add(new UserItem("User 3", R.drawable.round_menu_24));

        recyclerView = findViewById(R.id.user_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new UserAdapter(getApplicationContext(), userItems);
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
                    redirectActivity(UserList.this, UserList.class);
                } else if (id == R.id.trainer) {
                    redirectActivity(UserList.this, TrainerList.class);
                } else if (id == R.id.workout) {
                    redirectActivity(UserList.this, WorkoutList.class);
                } else if (id == R.id.diet) {
                    redirectActivity(UserList.this, DietList.class);
                } else {
                    Toast.makeText(UserList.this, "profile", Toast.LENGTH_SHORT).show();
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