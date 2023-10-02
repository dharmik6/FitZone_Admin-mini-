package com.example.fitzoneadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DietData extends AppCompatActivity {

    ImageView dietImage ;
    TextView dietName , dietDesc ;
    Button update , delete ;
    private StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_data);



        dietImage = findViewById(R.id.diet_image);
        dietName = findViewById(R.id.diet_name);
        dietDesc = findViewById(R.id.diet_description);
        update = findViewById(R.id.btn_Update);
        delete = findViewById(R.id.btn_Delete);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("diets");
        storageReference = FirebaseStorage.getInstance().getReference().child("diet_images");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(DietData.this, DietEdit.class);
                startActivity(homeIntent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DietData.this, "Record Deleted", Toast.LENGTH_SHORT).show();
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