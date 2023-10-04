package com.example.fitzoneadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DietAdd extends AppCompatActivity {

    EditText diet_name ,description ;
    Button add_diet , add_diet_image;
    ImageView diet_image ;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;
    DatabaseReference databaseReference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_add);
        diet_name = findViewById(R.id.add_diet_name);
        description = findViewById(R.id.add_diet_desc);
        add_diet = findViewById(R.id.btn_diet_add);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("diets");
        storageReference = FirebaseStorage.getInstance().getReference().child("diet_images");

        diet_image = findViewById(R.id.add_diet_image);
        add_diet_image = findViewById(R.id.import_diet_image);

        add_diet_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, PICK_IMAGE_REQUEST);
            }
        });

        add_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a ProgressDialog
                ProgressDialog pd = new ProgressDialog(DietAdd.this);
                pd.setMessage("Loading user data");
                pd.setCancelable(false);

                // Show the ProgressDialog
                pd.show();

                // Get the user input from EditText fields
                String dietName = diet_name.getText().toString();
                String dietDescription = description.getText().toString();

                // Check if the user has selected an image
                if (selectedImageUri != null) {
                    // Upload the image to Firebase Storage
                    StorageReference imageRef = storageReference.child(selectedImageUri.getLastPathSegment());
                    imageRef.putFile(selectedImageUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // Image upload success, get the download URL
                                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            // Create a Diet object with name, description, and image URL
                                            String imageUrl = uri.toString();
                                            DietItem diet = new DietItem(dietName, dietDescription, imageUrl);

                                            // Push the Diet object to the Firebase Realtime Database
                                            databaseReference.push().setValue(diet).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        // Diet data added to the database successfully
                                                        Toast.makeText(DietAdd.this, "Diet Added Successfully", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(DietAdd.this,DietList.class));
                                                        pd.dismiss();
                                                    } else {
                                                        // Handle the error
                                                        Toast.makeText(DietAdd.this, "Failed to add diet", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                } else {
                    pd.dismiss();
                    // Handle the case where the user didn't select an image
                    Toast.makeText(DietAdd.this, "Please select an image", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ImageView back_page = findViewById(R.id.btn_next_page);
        back_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
                diet_image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
