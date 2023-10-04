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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class WorkoutData extends AppCompatActivity {
    Button update , delete ;
    TextView wornames,des,focus;
    ImageView image;
    DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_workout_data);
        update = findViewById(R.id.btn_Update);
        delete = findViewById(R.id.btn_Delete);
        wornames=findViewById(R.id.worname);
        des=findViewById(R.id.des);
        focus=findViewById(R.id.focus);
        image=findViewById(R.id.image1);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("workouts");
//        storageReference = FirebaseStorage.getInstance().getReference().child("dietImageResourceId");

        focus.setText(getIntent().getStringExtra("wfocus"));
        wornames.setText(getIntent().getStringExtra("wname"));
        des.setText(getIntent().getStringExtra("wdes"));
        image.setImageResource(getIntent().getIntExtra("imag",0));
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ilogin = new Intent(WorkoutData.this , WorkoutEdit.class);
                startActivity(ilogin);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the workout name from the intent
                String workoutName = getIntent().getStringExtra("workoutname");

                // Delete the workout data
                DatabaseReference workoutRef = databaseReference.child("workouts").child(workoutName);
                workoutRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Workout data has been successfully deleted
                        Toast.makeText(WorkoutData.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                        onBackPressed(); // Go back to the previous screen
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the case where deletion fails
                        Toast.makeText(WorkoutData.this, "Failed to delete record: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        //**********************************
        //back page button
        ImageView back_page = findViewById(R.id.btn_next_page);
        back_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
// Get the workout name from the intent
//                String workoutName = getIntent().getStringExtra("workoutname");
//
//                // Delete the workout data
//                DatabaseReference workoutRef = databaseReference.child("workouts").child(workoutName);
//                workoutRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        // Workout data has been successfully deleted
//                        Toast.makeText(WorkoutData.this, "Record Deleted", Toast.LENGTH_SHORT).show();
//                        onBackPressed(); // Go back to the previous screen
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // Handle the case where deletion fails
//                        Toast.makeText(WorkoutData.this, "Failed to delete record: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });