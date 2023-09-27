package com.example.fitzoneadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class WorkoutAdd extends AppCompatActivity {

    EditText work_name , focuse_work , description ;
    Button add_work ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_add);

        work_name = findViewById(R.id.add_work_name);// workout name
        focuse_work = findViewById(R.id.add_focus_area);// focuse area
        description = findViewById(R.id.add_workout_desc);// description of workout
        add_work = findViewById(R.id.btn_work_add);

        //**********************************
        //add button click event
        add_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WorkoutAdd.this, "workout added", Toast.LENGTH_SHORT).show();
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