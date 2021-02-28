package com.example.livewell;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PostsBoardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_board);

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
