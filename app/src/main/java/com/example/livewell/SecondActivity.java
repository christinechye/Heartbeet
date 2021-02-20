package com.example.livewell;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    public final static String TAG = "SecondActivity";
    private String userName = "";

    TextView userText;

    ExpandableHeightListView listview1;
    Button Addbutton1;
    EditText GetValue1;
    String[] ListElements1 = new String[] {};

    ExpandableHeightListView listview2;
    Button Addbutton2;
    EditText GetValue2;
    String[] ListElements2 = new String[] {};

    ExpandableHeightListView listview3;
    Button Addbutton3;
    EditText GetValue3;
    String[] ListElements3 = new String[] {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"On Create");

        // set the UI layout for this activity
        setContentView(R.layout.activity_second);

        // initialize UI elements: usertext
        userText = findViewById(R.id.tv_user);

        //Get and set username
        userName = getIntent().getStringExtra("name");
        userText.setText(userName);

        // initialize UI elements: list view 1
        listview1 = (ExpandableHeightListView)findViewById(R.id.listView1);
        Addbutton1 = (Button)findViewById(R.id.button1);
        GetValue1 = (EditText)findViewById(R.id.editText1);

        // initialize UI elements: list view 2
        listview2 = (ExpandableHeightListView)findViewById(R.id.listView2);
        Addbutton2 = (Button)findViewById(R.id.button2);
        GetValue2 = (EditText)findViewById(R.id.editText2);

        // initialize UI elements: list view 3
        listview3 = (ExpandableHeightListView)findViewById(R.id.listView3);
        Addbutton3 = (Button)findViewById(R.id.button3);
        GetValue3 = (EditText)findViewById(R.id.editText3);

        // adapter for list 1
        final List<String> ListElementsArrayList1 = new ArrayList<String>(Arrays.asList(ListElements1));

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,
                        ListElementsArrayList1);

        listview1.setAdapter(adapter1);
        listview1.setExpanded(true);

        Addbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListElementsArrayList1.add(GetValue1.getText().toString());
                GetValue1.getText().clear();
                adapter1.notifyDataSetChanged();
            }
        });

        // adapter for list 2
        final List<String> ListElementsArrayList2 = new ArrayList<String>(Arrays.asList(ListElements2));

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,
                ListElementsArrayList2);

        listview2.setAdapter(adapter2);
        listview2.setExpanded(true);

        Addbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListElementsArrayList2.add(GetValue2.getText().toString());
                GetValue2.getText().clear();
                adapter2.notifyDataSetChanged();
            }
        });


        // adapter for list 3
        final List<String> ListElementsArrayList3 = new ArrayList<String>(Arrays.asList(ListElements3));

        final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,
                ListElementsArrayList3);

        listview3.setAdapter(adapter3);
        listview3.setExpanded(true);

        Addbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListElementsArrayList3.add(GetValue3.getText().toString());
                GetValue3.getText().clear();
                adapter3.notifyDataSetChanged();
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

    public void calculate(View view) {

    }

    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
