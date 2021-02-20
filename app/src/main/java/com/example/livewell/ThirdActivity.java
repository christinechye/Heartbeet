package com.example.livewell;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    public final static String TAG = "ThirdActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"On Create");

        // set the UI layout for this activity
        setContentView(R.layout.activity_third);
    }

    //ToDo: 1. Implement the callback methods
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "On Start");
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, "On Resume");
    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "On Pause");
    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, "On Stop");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "On Restart");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "On Destroy");
    }

    public void signup(View view) {
    }

    //ToDo: 3. Implement click event handler method
    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
