package com.example.livewell;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ThirdActivity extends AppCompatActivity {
    public final static String TAG = "ThirdActivity";

    public final static String USERNAME = "Christine@gmail.com";

    private FirebaseAuth fAuth;

    // get root node of the firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    EditText emailAddr;
    EditText password;
    EditText username;

    String gender;

    Button signupButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"On Create");

        // set the UI layout for this activity
        setContentView(R.layout.activity_third);

        // get and set password and username
        username = findViewById(R.id.et_name);
        emailAddr = findViewById(R.id.et_email1);
        password = findViewById(R.id.et_password1);

        signupButton = findViewById(R.id.btn_signup);

        fAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();

        signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String email = emailAddr.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String gen = gender;

                if (TextUtils.isEmpty(name)) {
                    username.setError("Your name is required.");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    username.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(pwd)) {
                    password.setError("Password is required.");
                    return;
                }

                if (gender.isEmpty()) {
                    Toast.makeText(ThirdActivity.this, "Gender is required", Toast.LENGTH_SHORT).show();
                    return;
                }

//                if (fAuth.getCurrentUser() != null) {
//                    Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
//                    startActivity(intent);
//                }

                Log.i(TAG,"checked fields");
                fAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        writeNewUser(name, gen);
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = user.getUid();

                            UserHelperClass newUser = new UserHelperClass(name, gen);

                            DatabaseReference myRef = database.getReference("users");
                            myRef.child(uid).setValue(newUser);
//                            myRef.child(uid).child("username").setValue(name);
//                            myRef.child(uid).child("gender").setValue(gen);

                            Toast.makeText(ThirdActivity.this, "User created.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ThirdActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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

//    public void signup(View view) {
//        String usernameInput = username.getText().toString();
//
//        if (usernameInput.compareTo(USERNAME) == 0) {
//            Context context = getApplicationContext();
//            CharSequence text = "You already have an account with this username.";
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//        }
//    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked)
                    gender = "Male";
                break;
            case R.id.radio_female:
                if (checked)
                    gender = "Female";
                break;
        }
    }

    //ToDo: 3. Implement click event handler method
    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

