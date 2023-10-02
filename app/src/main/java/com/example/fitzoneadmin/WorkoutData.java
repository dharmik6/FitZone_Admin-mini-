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
wornames.setText(getIntent().getStringExtra("wname"));
        des.setText(getIntent().getStringExtra("wfocus"));
        focus.setText(getIntent().getStringExtra("wdes"));
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
                // Get the username from the intent
                String username = getIntent().getStringExtra("workoutname");

                // Delete the user's data
                DatabaseReference userRef = databaseReference.getRoot().child("users").child(username);
                userRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // User data has been successfully deleted
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
                Toast.makeText(WorkoutData.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                onBackPressed();
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