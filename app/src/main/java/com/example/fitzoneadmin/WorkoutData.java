package com.example.fitzoneadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class WorkoutData extends AppCompatActivity {
    // Declare variables
    private String recordKey; // Workoutname as recordKey
    private TextView worknametxt, description, focus;
    private ImageView workimg;
    private Button update, delete;

    // Firebase variables
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_data);

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference("workouts");

        // Initialize UI elements
        worknametxt = findViewById(R.id.work_name);
        focus = findViewById(R.id.focus);
        description = findViewById(R.id.workdesc);
        workimg = findViewById(R.id.work_img);
        update = findViewById(R.id.btn_Update);
        delete = findViewById(R.id.btn_Delete);

        // Get workoutname from the intent and set it as recordKey
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("workoutname")) {
            recordKey = intent.getStringExtra("workoutname");

            // Load workout data using recordKey
            loadWorkoutData(recordKey);
        }

        // Update button click listener
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to WorkoutEdit activity with workoutname as an extra
                Intent iupdate = new Intent(WorkoutData.this, WorkoutEdit.class);
                iupdate.putExtra("workoutname", recordKey);
                startActivity(iupdate);
            }
        });

        // Delete button click listener
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the deleteWorkoutData method with recordKey
                deleteWorkoutData(recordKey);
            }
        });
    }

    // Method to load workout data using recordKey
    private void loadWorkoutData(String workoutname) {
        // Query the database to find the specific workout by workoutname
        Query query = databaseReference.orderByChild("workoutName").equalTo(workoutname);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve workout data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    WorkoutItem workoutItem = snapshot.getValue(WorkoutItem.class);
                    if (workoutItem != null) {
                        // Set data to UI elements
                        worknametxt.setText(workoutItem.getWorkoutName());
                        description.setText(workoutItem.getWorkoutDescription());
                        focus.setText(workoutItem.getWorkoutFocusArea());

                        // Load image using Glide
                        Glide.with(WorkoutData.this)
                                .load(workoutItem.getWorkoutImageResourceId())
                                .into(workimg);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(WorkoutData.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to delete workout data using recordKey
    private void deleteWorkoutData(String workoutname) {
        // Query the database to find the specific workout by workoutname
        Query query = databaseReference.orderByChild("workoutName").equalTo(workoutname);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Loop through the results and remove the workout
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                }
                Toast.makeText(WorkoutData.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors here
                Toast.makeText(WorkoutData.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
