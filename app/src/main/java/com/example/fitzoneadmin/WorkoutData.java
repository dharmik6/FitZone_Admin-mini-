package com.example.fitzoneadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class WorkoutData extends AppCompatActivity {
    Button update , delete ;
    TextView worknametxt,description,focus;
    ImageView image;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ImageView workimg ;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        delete = findViewById(R.id.btn_Delete);

        workimg=findViewById(R.id.work_img);
        database = FirebaseDatabase.getInstance();

        // Initialize your TextView elements
        worknametxt = findViewById(R.id.uname);
        focus = findViewById(R.id.uPhone);
        description = findViewById(R.id.uAge);

        // Retrieve the username from the intent
        String workname = getIntent().getStringExtra("workname");
        // Initialize the database reference to the specific user
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("workouts").child(workname);
        setDatabaseListener();

        worknametxt.setText(getIntent().getStringExtra("workname"));
        description.setText(getIntent().getStringExtra("workdescription"));
        focus.setText(getIntent().getStringExtra("focus"));


        String getWorkImage = getIntent().getStringExtra("workimage");

        Glide.with(WorkoutData.this)
                .load(getWorkImage) // Load image URL
                .into(workimg); // Set the loaded image to the ImageView
    }

    private void setDatabaseListener() {

    }

        //**********************************


}