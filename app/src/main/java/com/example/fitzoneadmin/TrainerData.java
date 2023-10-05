package com.example.fitzoneadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class TrainerData extends AppCompatActivity {
    TextView tname ,temail,tphone,tage ,tgender;
    ImageView timage ;
    DatabaseReference databaseReference;

    Button update , delete ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_data);

        tname = findViewById(R.id.tName);
        tage = findViewById(R.id.tAge);
        tgender = findViewById(R.id.tGender);
        temail = findViewById(R.id.tEmail);
        tphone = findViewById(R.id.tPhone);
        timage = findViewById(R.id.timage);
        delete = findViewById(R.id.btn_Delete);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrainerData.this, "Record Deleted", Toast.LENGTH_SHORT).show();
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
        //*****************************
        // Retrieve the username from the intent
        String trainername = getIntent().getStringExtra("trainername");

        // Initialize the database reference to the specific user
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("trainers").child(trainername);
        setDatabaseListener();

        tname.setText(getIntent().getStringExtra("trainername"));
        tage.setText(getIntent().getStringExtra("age"));
        tgender.setText(getIntent().getStringExtra("gender"));
        temail.setText(getIntent().getStringExtra("email"));
        tphone.setText(getIntent().getStringExtra("number"));




        String getUserImage = getIntent().getStringExtra("trainerimage");

        Glide.with(TrainerData.this)
                .load(getUserImage) // Load image URL
                .into(timage); // Set the loaded image to the ImageView
    }
    private void setDatabaseListener() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the email from the intent
                final String email = getIntent().getStringExtra("email");

                // Get reference to the "trainers" node in the Firebase database
                DatabaseReference trainersRef = FirebaseDatabase.getInstance().getReference("trainers");

                // Query the database to find the specific trainer by email
                Query query = trainersRef.orderByChild("email").equalTo(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Loop through the dataSnapshot and delete the trainer with the specified email
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                snapshot.getRef().removeValue()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Trainer has been successfully deleted from the Realtime Database
                                                Toast.makeText(TrainerData.this, "Trainer Record Deleted", Toast.LENGTH_SHORT).show();
                                                onBackPressed(); // Navigate back after deletion
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Handle errors here
                                                Toast.makeText(TrainerData.this, "Failed to delete trainer record: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        } else {
                            // Trainer with the specified email does not exist in the Realtime Database
                            Toast.makeText(TrainerData.this, "Trainer not found in the database", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle any errors here
                        Toast.makeText(TrainerData.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}