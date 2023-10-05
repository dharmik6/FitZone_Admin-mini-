package com.example.fitzoneadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TrainerAdd extends AppCompatActivity {
    EditText add_tr_name,add_tr_gender,add_tr_phone,add_tr_age,add_tr_email,add_tr_pwd;
    Button btn_work_add;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_add);

        add_tr_age=findViewById(R.id.add_tr_age);
        add_tr_name=findViewById(R.id.add_tr_name);
        add_tr_gender=findViewById(R.id.add_tr_gender);
        add_tr_phone=findViewById(R.id.add_tr_phone);
        add_tr_email=findViewById(R.id.add_tr_email);
        add_tr_pwd=findViewById(R.id.add_tr_pwd);

        btn_work_add=findViewById(R.id.btn_work_add);

        // Initialize Firebase Authentication and Realtime Database
        auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("trainers");

        // Back button functionality
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        btn_work_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = add_tr_email.getText().toString();
                String pass = add_tr_pwd.getText().toString();
                String name = add_tr_name.getText().toString();
                String number = add_tr_phone.getText().toString();
                String age = add_tr_age.getText().toString();
                String gender = add_tr_gender.getText().toString();

                // Validation of all fields
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(TrainerAdd.this, "Name is compulsory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(number)) {
                    Toast.makeText(TrainerAdd.this, "Phone number is compulsory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(TrainerAdd.this, "Email is compulsory", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(TrainerAdd.this, "Invalid Email Address!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(TrainerAdd.this, "Password is compulsory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(age)) {
                    Toast.makeText(TrainerAdd.this, "Age is compulsory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(gender)) {
                    Toast.makeText(TrainerAdd.this, "Gender is compulsory", Toast.LENGTH_SHORT).show();
                } else {
                    // Create a new user with email and password using Firebase Authentication
                    auth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(TrainerAdd.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser tenanr = auth.getCurrentUser();
                                        // Store additional user data in the Realtime Database
                                        saveUserDataToDatabase(tenanr.getUid(), name, number, email, age, gender);
                                        // Proceed to the next activity or perform any desired actions
                                        Toast.makeText(TrainerAdd.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(TrainerAdd.this, TrainerList.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        String errorMessage = task.getException().getMessage();
                                        Toast.makeText(TrainerAdd.this, "Registration failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }

    private void saveUserDataToDatabase(String userId, String name, String number, String email, String age, String gender) {
        if (databaseReference != null) {
            DatabaseReference tenanrRef = databaseReference.child(userId);
            tenanrRef.child("name").setValue(name);
            tenanrRef.child("Number").setValue(number);
            tenanrRef.child("email").setValue(email);
            tenanrRef.child("age").setValue(age);
            tenanrRef.child("gender").setValue(gender);
        } else {
            // Handle the case where databaseReference is null
            Toast.makeText(TrainerAdd.this, "Database reference is null", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();

    } }