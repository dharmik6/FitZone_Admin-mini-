package com.example.fitzoneadmin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AdminHomePage extends AppCompatActivity {
    CardView user ,trainer , diet ,work;
    DrawerLayout drawerLayout ;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        user = findViewById(R.id.cd_user);
        trainer = findViewById(R.id.cd_trainer);
        diet = findViewById(R.id.cd_diet);
        work = findViewById(R.id.cd_work);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(AdminHomePage.this,UserList.class);
                startActivity(user);
            }
        });
        trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(AdminHomePage.this,TrainerList.class);
                startActivity(user);
            }
        });
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(AdminHomePage.this,DietList.class);
                startActivity(user);
            }
        });
        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(AdminHomePage.this,WorkoutList.class);
                startActivity(user);
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
                    redirectActivity(AdminHomePage.this, UserList.class);
                } else if (id == R.id.trainer) {
                    redirectActivity(AdminHomePage.this, TrainerList.class);
                } else if (id == R.id.workout) {
                    redirectActivity(AdminHomePage.this, WorkoutList.class);
                } else if (id == R.id.diet) {
                    redirectActivity(AdminHomePage.this, DietList.class);
                }
                else if (id == R.id.logout)
                {
                    SharedPreferences pref = getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("flag", false);
                    editor.apply();

                    // After logging out, navigate to the LoginActivity
                    Intent intent = new Intent(AdminHomePage.this, AdminLogin.class);
                    startActivity(intent);
                    finish(); // Close th
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