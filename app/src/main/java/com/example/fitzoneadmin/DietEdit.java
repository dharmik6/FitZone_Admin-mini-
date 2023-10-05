package com.example.fitzoneadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DietEdit extends AppCompatActivity {
    Button update, import_diet_image;
    ImageView change_diet_image;
    EditText chg_diet_name, change_diet_desc;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_edit);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("diets");

        // Update data in the database
        updateData();

        // Use putExtra to attach the data to the Intent


        update = findViewById(R.id.updateData);
        chg_diet_name = findViewById(R.id.chg_diet_name);
        change_diet_desc = findViewById(R.id.change_diet_desc);
        import_diet_image = findViewById(R.id.btn_change_diet_image);
        change_diet_image = findViewById(R.id.change_diet_image);

        import_diet_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, PICK_IMAGE_REQUEST);
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

    private void updateData() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // Get the data from the input fields
//                String dietName = chg_diet_name.getText().toString();
//                String dietDescription = change_diet_desc.getText().toString();
//
//                // Check if the user has selected an image
//                if (selectedImageUri != null) {
//                    // Upload the image to Firebase Storage (you should add this part)
//
//                    // Create a Diet object with updated data (including image URL)
//                    DietItem diet = new DietItem(dietName, dietDescription, "image_url_here");
//
//                    // Push the updated Diet object to the Firebase Realtime Database
//                    String dietId = databaseReference.push().getKey(); // Generate a unique key for the new data
//                    databaseReference.child(dietId).setValue(diet).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                // Data updated successfully
//                                Toast.makeText(DietEdit.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
//                            } else {
//                                // Data update failed
//                                Toast.makeText(DietEdit.this, "Data update failed", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                } else {
//                    // Handle the case where the user didn't select an image
//                    Toast.makeText(DietEdit.this, "Please select an image", Toast.LENGTH_SHORT).show();
//                }

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();

            try {
                // Load the selected image into the ImageView
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                change_diet_image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
