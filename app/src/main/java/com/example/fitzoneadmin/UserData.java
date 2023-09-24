package com.example.fitzoneadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class UserData extends AppCompatActivity {
    Button update , delete ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        update = findViewById(R.id.btn_Update);
        delete = findViewById(R.id.btn_Delete);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserData.this, "Record Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserData.this, "Record Deleted", Toast.LENGTH_SHORT).show();
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