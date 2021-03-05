package com.example.livewell;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostsBoardActivity extends AppCompatActivity {
    private final static String TAG = "PostsBoardActivity";

    private RecyclerView blog_list_view;
    private List<BlogPost> blog_list;

    FirebaseDatabase database;
    DatabaseReference postsRef;

    private BlogRecyclerAdapter blogRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_board);

        blog_list = new ArrayList<>();
        blog_list_view = findViewById(R.id.blog_post);

        blog_list_view.setLayoutManager(new LinearLayoutManager(PostsBoardActivity.this));

        blogRecyclerAdapter = new BlogRecyclerAdapter(blog_list);

        blog_list_view.setAdapter(blogRecyclerAdapter);

        // get data from firebase
        database = FirebaseDatabase.getInstance();

        postsRef = database.getReference("posts");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                blog_list.clear();

                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    BlogPost post = keyNode.getValue(BlogPost.class);
                    blog_list.add(post);
                }

                blogRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        postsRef.addValueEventListener(postListener);


        Button here = (Button) findViewById(R.id.here);
        here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(PostsBoardActivity.this, PostBoardActivity.class);
                startActivity(test);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        Intent intent1 = new Intent(PostsBoardActivity.this, SecondActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.page_2:
                        Intent intent2 = new Intent(PostsBoardActivity.this, FifthActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.page_3:
                        break;
                }

                return true;
            }
        });
    }
}
