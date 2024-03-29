package com.example.fitzoneadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class UserData extends AppCompatActivity {
    // Define your TextView elements
    TextView nameTextView,weightTextView,numberTextView,ageTextView,heightTextView,emailTextView,genderTextView;
    Button delete ;
    DatabaseReference databaseReference;

    FirebaseDatabase database;
    CircleImageView userimg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        delete = findViewById(R.id.btn_Delete);

        userimg=findViewById(R.id.uimage);
        database = FirebaseDatabase.getInstance();

        // Initialize your TextView elements
        nameTextView = findViewById(R.id.uname);
        numberTextView = findViewById(R.id.uPhone);
        ageTextView = findViewById(R.id.uAge);
        genderTextView = findViewById(R.id.uGender);
        emailTextView = findViewById(R.id.uEmail);
        heightTextView = findViewById(R.id.uHeight);
        weightTextView = findViewById(R.id.uWeight);

        // Retrieve the username from the intent
        String username = getIntent().getStringExtra("username");

        // Initialize the database reference to the specific user
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users").child(username);
        setDatabaseListener();

        nameTextView.setText(getIntent().getStringExtra("username"));
        numberTextView.setText(getIntent().getStringExtra("number"));
        emailTextView.setText(getIntent().getStringExtra("email"));
        genderTextView.setText(getIntent().getStringExtra("gender"));
        heightTextView.setText(getIntent().getStringExtra("height"));
        weightTextView.setText(getIntent().getStringExtra("weight"));
        ageTextView.setText(getIntent().getStringExtra("age"));


        String getUserImage = getIntent().getStringExtra("userimage");

        Glide.with(UserData.this)
                .load(getUserImage) // Load image URL
                .into(userimg); // Set the loaded image to the ImageView
    }


    private void setDatabaseListener() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the email from the intent
                final String email = getIntent().getStringExtra("email");

                // Get reference to the "users" node in the Firebase database
                DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

                // Query the database to find the specific user by email
                Query query = usersRef.orderByChild("email").equalTo(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Loop through the dataSnapshot and delete the user with the specified email
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                snapshot.getRef().removeValue()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // User has been successfully deleted from the Realtime Database
                                                Toast.makeText(UserData.this, "User Record Deleted", Toast.LENGTH_SHORT).show();
                                                onBackPressed(); // Navigate back after deletion
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Handle errors here
                                                Toast.makeText(UserData.this, "Failed to delete user record: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        } else {
                            // User with the specified email does not exist in the Realtime Database
                            Toast.makeText(UserData.this, "User not found in the database", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle any errors here
                        Toast.makeText(UserData.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}