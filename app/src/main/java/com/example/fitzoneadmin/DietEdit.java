package com.example.fitzoneadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DietEdit extends AppCompatActivity {
    Button submit ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_edit);

        submit = findViewById(R.id.btn_diet_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DietEdit.this, "Record Updated", Toast.LENGTH_SHORT).show();
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