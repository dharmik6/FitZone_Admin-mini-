package com.example.fitzoneadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DietAdd extends AppCompatActivity {

    EditText diet_name ,description ;
    Button add_diet ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_add);
        diet_name = findViewById(R.id.add_diet_name);// workout name

        description = findViewById(R.id.add_diet_desc);// description of workout
        add_diet = findViewById(R.id.btn_diet_add);

        //**********************************
        //add button click event
        add_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DietAdd.this, "diet added", Toast.LENGTH_SHORT).show();
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