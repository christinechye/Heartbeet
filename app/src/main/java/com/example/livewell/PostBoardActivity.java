package com.example.livewell;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostBoardActivity extends AppCompatActivity {

    // get root node of the firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    // init all objects to handle the tasks
    ImageButton image_btn;
    ImageView image_view;
    EditText title;
    EditText description;

    Button post;

    // Accessing user info on Firebase (create a reference to it)
//    DatabaseReference myUserRef = FirebaseDatabase.getInstance().getReference().child("users");

    static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentPhotoPath;
    private Uri photoURI;

    private ProgressBar newPostProgress;
    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myuserRef;
    private String current_user_id;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_board);

        // get and set objects
        image_btn = (ImageButton) findViewById(R.id.imageButton);
        image_view = (ImageView) findViewById(R.id.img_display);
        title = findViewById(R.id.et_title);
        description = findViewById(R.id.et_desc);
        post = findViewById(R.id.btn_post);

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        current_user_id = firebaseAuth.getCurrentUser().getUid();
        myuserRef = FirebaseDatabase.getInstance().getReference("posts");

        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
//                onActivityResult();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the text from objects
                String post_title = title.getText().toString().trim();
                String post_desc = description.getText().toString().trim();

                if (image_view.getDrawable() == null) {
                    Toast.makeText(PostBoardActivity.this, "Image is required.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(post_title)) {
                    title.setError("Post title is required.");
                    return;
                }

                if (TextUtils.isEmpty(post_desc)) {
                    description.setError("Post description is required.");
                    return;
                }
                String randomName = FieldValue.serverTimestamp().toString();
                StorageReference filePath = storageReference.child("post_images").child(randomName + ".jpg");
                filePath.putFile(photoURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                final Uri downloadUrl = uri;
//                                Map<String, Object> postMap = new HashMap<>();
//                                postMap.put("image_url", downloadUrl);
//                                postMap.put("title", post_title);
//                                postMap.put("desc", post_desc);
//                                postMap.put("user_id", current_user_id);
//                                postMap.put("timestamp", FieldValue.serverTimestamp());

                                PostHelperClass newPost = new PostHelperClass(downloadUrl, post_title, post_desc, current_user_id, FieldValue.serverTimestamp().toString());
                                myuserRef.setValue(newPost);

                                Toast.makeText(PostBoardActivity.this, "Success! Post successful.", Toast.LENGTH_SHORT).show();


//                                addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<DocumentReference> task) {
//                                        if (task.isSuccessful()) {
//                                            Toast.makeText(PostBoardActivity.this, "Post was added.", Toast.LENGTH_SHORT).show();
//                                            Intent mainIntent = new Intent (PostBoardActivity.this, PostsBoardActivity.class);
//                                            startActivity(mainIntent);
//
//                                        }
//                                        else {
//                                            Toast.makeText(PostBoardActivity.this, "Error! Post not successful.", Toast.LENGTH_SHORT).show();
//                                        }
//                                        newPostProgress.setVisibility(View.INVISIBLE);
//
//                                    }
//                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(PostBoardActivity.this, "Error! Post not successful.", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            File f = new File(currentPhotoPath);
            image_view.setImageURI(Uri.fromFile(f));
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(PostBoardActivity.this, "Error! Cannot take photo.", Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
}
