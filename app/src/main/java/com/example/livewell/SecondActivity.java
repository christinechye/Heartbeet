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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    public final static String TAG = "SecondActivity";
    private String userName = "";

    // declares the queue
    private RequestQueue queue;

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

        // initialize the queue
        queue = Volley.newRequestQueue(this);

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
            String item1;

            @Override
            public void onClick(View v) {
                // get the name of the item
                item1 = GetValue1.getText().toString();

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
            String item2;

            @Override
            public void onClick(View v) {
                // get name for item2
                item2 = GetValue2.getText().toString();

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
            String item3;

            @Override
            public void onClick(View v) {
                // get name for item3
                item3 = GetValue3.getText().toString();

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

    private StringRequest searchNameStringRequest(String nameSearch) {
        final String API = "api_key=81WgY3Nd86grdI3FTtaYuE1ndBqvNTjNnwXB403a";
        final String NAME_SEARCH = "&query=";
//        final String DATA_SOURCE = "&ds=Standard Reference";
//        final String FOOD_GROUP = "&fg=";
//        final String SORT = "&sort=r";
//        final String MAX_ROWS = "&max=25";
//        final String BEGINNING_ROW = "&offset=0";
        final String URL_PREFIX = "https://api.nal.usda.gov/fdc/v1/foods/search?";

        String url = URL_PREFIX + API + NAME_SEARCH + nameSearch;

        // 1st param => type of method (GET/PUT/POST/PATCH/etc)
        // 2nd param => complete url of the API
        // 3rd param => Response.Listener -> Success procedure
        // 4th param => Response.ErrorListener -> Error procedure
        return new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    // 3rd param - method onResponse lays the code procedure of success return
                    // SUCCESS
                    @Override
                    public void onResponse(String response) {
                        // try/catch block for returned JSON data
                        // see API's documentation for returned format
                        try {
                            JSONObject result = new JSONObject(response).getJSONObject("list");
                            int maxItems = result.getInt("end");
                            JSONArray resultList = result.getJSONArray("item");
                            // catch for the JSON parsing error
                        } catch (JSONException e) {
                            Toast.makeText(SecondActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } // public void onResponse(String response)
                },
                new Response.ErrorListener() {
                    // 4th param - method onErrorResponse lays the code procedure of error return
                    // ERROR
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // display a simple message on the screen
                        Toast.makeText(SecondActivity.this, "Food source is not responding (USDA API)", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
