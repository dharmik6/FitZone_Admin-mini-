package com.example.fitzoneadmin;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AdminHomePage extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout_home);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.navigationview);
        setupNavigationDrawer();
    }

    private void setupNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle item clicks here
                int id = item.getItemId();
                if (id == R.id.user) {
                    // Handle user item click
                    Toast.makeText(AdminHomePage.this, "User", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.trainer) {
                    // Handle trainer item click
                    Toast.makeText(AdminHomePage.this, "Trainer", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.workout) {
                    // Handle workout item click
                    Toast.makeText(AdminHomePage.this, "Workout", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.diet) {
                    // Handle diet item click
                    Toast.makeText(AdminHomePage.this, "Diet", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.profile) {
                    // Handle profile item click
                    Toast.makeText(AdminHomePage.this, "Profile", Toast.LENGTH_SHORT).show();
                }

                // Close the drawer after handling the click event
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
