package com.example.livewell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";
    public final static String USERNAME = "Christine";
    public final static String PASSWORD = "123456";

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "On Create");

        // set the UI layout for this activity
        setContentView(R.layout.activity_main);

        // initialize UI elements
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
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

    //ToDo: 2. Implement click event handler method
    public void login(View view) {
        // input
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();

        // validate username
        if (usernameInput.compareTo(USERNAME) == 0 && passwordInput.compareTo(PASSWORD) == 0) {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("name", USERNAME);
            startActivity(intent);
        } else { // otherwise: toast message
            Context context = getApplicationContext();
            CharSequence text = "Invalid Username or Password.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void signup(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }
}