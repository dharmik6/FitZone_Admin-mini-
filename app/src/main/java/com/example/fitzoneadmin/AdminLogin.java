package com.example.fitzoneadmin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLogin extends AppCompatActivity {

    EditText admin_email, admin_password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        admin_email = findViewById(R.id.txt_admin_id);
        admin_password = findViewById(R.id.txt_admin_password);
        login = findViewById(R.id.admin_login);

        String ad_email = "dharmik.kacha.2526@gmail.com";
        String ad_pwd = "admin";

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = admin_email.getText().toString().trim();
                String inputPassword = admin_password.getText().toString().trim();

                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                Boolean check = pref.getBoolean("flag", false);
                Intent inext2;
                if (check) {
                    // User is logged in, navigate to home_page
                    inext2 = new Intent(AdminLogin.this, AdminHomePage.class);
                } else {
                    // User is not logged in, navigate to login_page
                    inext2 = new Intent(AdminLogin.this, AdminLogin.class);
                }
                if (TextUtils.isEmpty(inputEmail)) {
                    admin_email.setError("Email is compulsory");
                    return;
                }

                if (TextUtils.isEmpty(inputPassword)) {
                    admin_password.setError("Password is compulsory");
                    return;
                }

                // Check if input email and password match the hardcoded values
                if (inputEmail.equals(ad_email) && inputPassword.equals(ad_pwd)) {
                    // Credentials match, perform intent operation
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putBoolean("flag" ,true);
                    editor.apply();
                    // Sign-in successful, navigate to the next page
                    Intent intent = new Intent(AdminLogin.this, AdminHomePage.class);
                    startActivity(intent);
                    finish(); // Optional: Close the login activity
                } else {
                    // Credentials do not match, show a toast message
                    Toast.makeText(AdminLogin.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
