package com.example.livewell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";
    public final static String USERNAME = "Christine@gmail.com";
    public final static String PASSWORD = "123456";

    private FirebaseAuth fAuth;

    EditText username;
    EditText password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "On Create");

        // set the UI layout for this activity
        setContentView(R.layout.activity_main);

        // initialize UI elements
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);

        loginButton = findViewById(R.id.btn_login);

        fAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    username.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(pwd)) {
                    password.setError("Password is required.");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String usernameInput = username.getText().toString();
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                            int atIndex = usernameInput.indexOf("@");
                            String username = usernameInput.substring(0, atIndex);
                            intent.putExtra("name", username);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

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
//    public void login(View view) {
//        // input
//        String usernameInput = username.getText().toString();
//        String passwordInput = password.getText().toString();
//
//        // validate username
//        if (usernameInput.compareTo(USERNAME) == 0 && passwordInput.compareTo(PASSWORD) == 0) {
//            Intent intent = new Intent(this, SecondActivity.class);
//            String username = usernameInput.substring(0, 9);
//            intent.putExtra("name", username);
//            startActivity(intent);
//        } else { // otherwise: toast message
//            Context context = getApplicationContext();
//            CharSequence text = "Invalid Username or Password.";
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//        }
//    }

    public void signup(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

//    public void TestNetwork(View view) {
//        Intent intent = new Intent(this, TestNetwork.class);
//        startActivity(intent);
//    }
}