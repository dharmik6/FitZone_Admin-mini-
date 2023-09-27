package com.example.fitzoneadmin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



public class AdminLogin extends AppCompatActivity {

    EditText admin_email , admin_password ;
    Button login ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        admin_email = (EditText) findViewById(R.id.txt_admin_id);
        admin_password = (EditText) findViewById(R.id.txt_admin_password);
        login = findViewById(R.id.admin_login);


        //------------sign in button----------------
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //*******************************
                // start validation
                if (TextUtils.isEmpty(admin_email.getText().toString())) {
                    admin_email.setError("Email is compulsary");

                }
                if (TextUtils.isEmpty(admin_password.getText().toString())) {
                    admin_password.setError("Password is compulsary");
                } else {
                    Intent homeIntent = new Intent(AdminLogin.this, AdminHomePage.class);
                    startActivity(homeIntent);
                }

            }
        });
    }
}

