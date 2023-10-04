package com.example.fitzoneadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DietData extends AppCompatActivity {

    ImageView dietImage ;
    TextView dietName , dietDesc ;
    Button update , delete ;
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

        databaseReference = FirebaseDatabase.getInstance().getReference();

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        databaseReference = database.getReference("diets");
//        storageReference = FirebaseStorage.getInstance().getReference().child("diet_images");

//        String dietNameValue = getIntent().getStringExtra("dname");
//        dietDesc.setText(getIntent().getStringExtra("desc"));
//        String dietimg = getIntent().getStringExtra("imag");


        dietName.setText(getIntent().getStringExtra("dname"));
        dietDesc.setText(getIntent().getStringExtra("desc"));
        dietImage.setImageResource(getIntent().getIntExtra("imag",0));
        deleteDataOneItemAtATime();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(DietData.this, DietEdit.class);
//                // Get the values you want to pass to DietEdit
//                String dietNameValue = dietName.getText().toString();
//                String dietDescValue = dietDesc.getText().toString();

                startActivity(homeIntent);
            }
        });

    }

    private void deleteDataOneItemAtATime() {
        // Retrieve the dietName from the Intent
        String dietNameValue = getIntent().getStringExtra("dname");


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dietNameValue != null && !dietNameValue.isEmpty()) {
                    // Reference the specific diet item to be deleted by dietName
                    DatabaseReference dietItemRef = databaseReference.child("diets").child(dietNameValue);

                    // Delete the diet item
                    dietItemRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Diet item deleted successfully
                                Toast.makeText(DietData.this, "Diet Item Deleted", Toast.LENGTH_SHORT).show();
                                onBackPressed(); // Go back to the previous screen
                            } else {
                                // Handle the case where deletion fails
                                Toast.makeText(DietData.this, "Failed to delete diet item", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    // Handle the case where the diet item name is missing or invalid
                    Toast.makeText(DietData.this, "Invalid diet item name", Toast.LENGTH_SHORT).show();
                }

//                // Retrieve the unique identifier (e.g., diet item ID) of the diet item
//                String dietItemId = getIntent().getStringExtra(String.valueOf(dietName));
//
//                // Ensure that you have a valid diet item ID
//                if (dietItemId != null && !dietItemId.isEmpty()) {
//                    // Reference the specific diet item to be deleted
//                    DatabaseReference dietItemRef = databaseReference.child("diets").child(dietItemId);
//
//                    // Delete the diet item
//                    dietItemRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                // Diet item deleted successfully
//                                Toast.makeText(DietData.this, "Diet Item Deleted", Toast.LENGTH_SHORT).show();
//                                onBackPressed(); // Go back to the previous screen
//                            } else {
//                                // Handle the case where deletion fails
//                                Toast.makeText(DietData.this, "Failed to delete diet item", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                } else {
//                    // Handle the case where the diet item ID is missing or invalid
//                    Toast.makeText(DietData.this, "Invalid diet item ID", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(DietData.this, "Record Deleted", Toast.LENGTH_SHORT).show();
//                onBackPressed();
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