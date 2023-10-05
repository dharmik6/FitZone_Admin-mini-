package com.example.fitzoneadmin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class WorkoutEdit extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private String recordKey; // Unique key for the record
    private EditText workoutNameEditText, focusAreaEditText, workoutDescriptionEditText;
    ImageView workoutimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_edit);

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("workouts");

        // Back page button
        ImageView backPage = findViewById(R.id.btn_next_page);
        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Initialize EditText fields
        workoutNameEditText = findViewById(R.id.change_work_name);
        focusAreaEditText = findViewById(R.id.change_focus_area);
        workoutDescriptionEditText = findViewById(R.id.change_workout_desc);
        workoutimage = findViewById(R.id.change_work_image);
        Button updateButton = findViewById(R.id.btn_work_submit);

        Intent intent = getIntent();
        if (intent != null) {
            recordKey = intent.getStringExtra("workoutname");

            // Ensure recordKey is not null before using it in the updateWorkoutData method
            if (recordKey != null) {
                // Set the initial values of EditText fields based on the retrieved recordKey
                workoutNameEditText.setText(recordKey);
                // You can retrieve other fields and set their initial values here if needed
            } else {
                // Handle the case where recordKey is null, perhaps show an error message or go back to the previous activity
                Toast.makeText(this, "Invalid workout name", Toast.LENGTH_SHORT).show();
                onBackPressed(); // Go back to the previous activity
            }
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get updated values from EditText fields
                String updatedWorkoutName = workoutNameEditText.getText().toString().trim();
                String updatedFocusArea = focusAreaEditText.getText().toString().trim();
                String updatedDescription = workoutDescriptionEditText.getText().toString().trim();

                // Update workout data in Firebase Database
                updateWorkoutData(updatedWorkoutName, updatedFocusArea, updatedDescription);
            }
        });
    }
    private void updateWorkoutData(String updatedWorkoutName, String updatedFocusArea, String updatedDescription) {
        DatabaseReference workoutRef = databaseReference.child(recordKey); // Use recordKey here

        HashMap<String, Object> updatedData = new HashMap<>();
        updatedData.put("workoutName", updatedWorkoutName);
        updatedData.put("workoutFocusArea", updatedFocusArea);
        updatedData.put("workoutDescription", updatedDescription);

        workoutRef.updateChildren(updatedData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Data updated successfully
                        Toast.makeText(WorkoutEdit.this, "Workout data updated successfully", Toast.LENGTH_SHORT).show();
                        // Redirect to another activity or perform necessary actions
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to update data
                        Toast.makeText(WorkoutEdit.this, "Failed to update workout data", Toast.LENGTH_SHORT).show();
                    }
                });
    }



}
