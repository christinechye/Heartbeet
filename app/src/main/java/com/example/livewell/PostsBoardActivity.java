package com.example.livewell;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PostsBoardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_board);


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
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
                        Intent intent3 = new Intent(PostsBoardActivity.this, PostsBoardActivity.class);
                        startActivity(intent3);
                }

                return true;
            }
        });
    }
}
