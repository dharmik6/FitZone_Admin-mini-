package com.example.fitzoneadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {

    EditText admin_id , admin_password ;
    Button login ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        admin_id = (EditText) findViewById(R.id.txt_admin_id);
        admin_password = (EditText) findViewById(R.id.txt_admin_password);
        login = findViewById(R.id.admin_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(admin_id.getText().toString(), admin_password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign-in successful, navigate to the next page
                                    Intent genderPageIntent = new Intent(AdminLogin.this, AdminHomePage.class);
                                    startActivity(genderPageIntent);
                                } else {
                                    // Sign-in failed, display an error message
                                    Toast.makeText(AdminLogin.this,"Please enter the correct Email and password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}