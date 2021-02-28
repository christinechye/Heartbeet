package com.example.livewell;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    DatabaseReference myUserRef = FirebaseDatabase.getInstance().getReference().child("users");

    static final int REQUEST_IMAGE_CAPTURE = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_board);

        // get and set objects
        image_btn = (ImageButton) findViewById(R.id.imageButton);
        image_view = (ImageView) findViewById(R.id.img_display);
        title = findViewById(R.id.et_title);
        description = findViewById(R.id.et_desc);
        post = findViewById(R.id.btn_post);

        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
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

            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
            Toast.makeText(PostBoardActivity.this, "Error! Cannot take photo.", Toast.LENGTH_SHORT).show();

        }
    }
}
