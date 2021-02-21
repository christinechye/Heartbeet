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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    public final static String TAG = "SecondActivity";
    private String userName = "";
    int calcium_req = 1000;
    int calcium_curr = 0;
    int iron_req_m = 8;
    int iron_req_f = 18;
    int iron_curr = 0;
    int mag_req_m = 400;
    int mag_req_f = 310;
    int mag_curr = 0;
    int pot_req = 3500;
    int pot_curr = 0;
    int vitA_req_m = 900;
    int vitA_req_f = 700;
    int vitA_curr = 0;
    int vitC_req_m = 90;
    int vitC_req_f = 75;
    int vitC_curr = 0;
    int vitD_req = 15;
    int vitD_curr = 0;
    int vitE_req = 15;
    int vitE_curr = 0;
    Boolean isFemale = true;
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
//                queue.cancelAll(pot_curr);
                // get name for item3
                item3 = GetValue3.getText().toString();

                jsonParse(item3);
                Toast.makeText(SecondActivity.this, String.valueOf(calcium_curr), Toast.LENGTH_LONG).show();

                ListElementsArrayList3.add(GetValue3.getText().toString());
                GetValue3.getText().clear();
                adapter3.notifyDataSetChanged();
            }
        });
    }

    private void jsonParse(String item1) {
        final String API = "api_key=81WgY3Nd86grdI3FTtaYuE1ndBqvNTjNnwXB403a";
        final String NAME_SEARCH = "&query=";
        final String URL_PREFIX = "https://api.nal.usda.gov/fdc/v1/foods/search?";
        String url = URL_PREFIX + API + NAME_SEARCH + item1;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("foods");
                            int flag = 0;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject foods = jsonArray.getJSONObject(i);
                                String food_name = foods.getString("lowercaseDescription");
//                                Toast.makeText(SecondActivity.this, food_name, Toast.LENGTH_LONG).show();
                                if (food_name.compareTo(item1) == 0 && flag == 0) {
                                    flag = 1;
                                    JSONArray nutrients = foods.getJSONArray("foodNutrients");
                                    for (int j = 0; j < nutrients.length(); j++) {
                                        JSONObject nutrientObj = nutrients.getJSONObject(j);
                                        String nutrientName = nutrientObj.getString("nutrientName");
                                        if (nutrientName.compareTo("Calcium, Ca") == 0) {
                                            calcium_curr += nutrientObj.getInt("nutrientNumber");
                                        }
                                        if (nutrientName.compareTo("Iron, Fe") == 0) {
                                            iron_curr += nutrientObj.getInt("nutrientNumber");
                                        }
                                        if (nutrientName.compareTo("Magnesium, Mg") == 0) {
                                            mag_curr += nutrientObj.getInt("nutrientNumber");
                                        }
                                        if (nutrientName.compareTo("Potassium, K") == 0) {
                                            pot_curr += nutrientObj.getInt("nutrientNumber");
                                        }
                                        if (nutrientName.compareTo("Vitamin A, IU") == 0) {
                                            vitA_curr += nutrientObj.getInt("nutrientNumber");
                                        }
                                        if (nutrientName.compareTo("Vitamin C, total ascorbic acid") == 0) {
                                            vitC_curr += nutrientObj.getInt("nutrientNumber");
                                        }
                                        if (nutrientName.compareTo("Vitamin D (D2 + D3)") == 0) {
                                            vitD_curr += nutrientObj.getInt("nutrientNumber");
                                        }
                                        if (nutrientName.compareTo("Vitamin E (alpha-tocopherol)") == 0) {
                                            vitE_curr += nutrientObj.getInt("nutrientNumber");
                                        }
                                    }
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(SecondActivity.this, "Food source is not responding (USDA API)", Toast.LENGTH_LONG).show();
//                parseVolleyError(error);
                String body = "String of network response";
                //get status code here
                String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                if(error.networkResponse.data!=null) {
                    try {
                        body = new String(error.networkResponse.data,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(SecondActivity.this, body, Toast.LENGTH_LONG).show();
            }

        });
        queue.add(request);
    }
//    public void parseVolleyError(VolleyError error) {
//        String json;
//        void volleyError;
//        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
//            try {
//                json = new String(volleyError.networkResponse.data,
//                        HttpHeaderParser.parseCharset(volleyError.networkResponse.headers));
//            } catch (UnsupportedEncodingException e) {
//                return new VolleyError(e.getMessage());
//            }
//            return new VolleyError(json);
//        }
//        return volleyError;
//    }

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
