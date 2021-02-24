package com.example.livewell;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    ExpandableHeightListView listview12;
    EditText GetSize1;
    String[] ListElementSize1 = new String[] {};

    ExpandableHeightListView listview2;
    Button Addbutton2;
    EditText GetValue2;
    String[] ListElements2 = new String[] {};
    ExpandableHeightListView listview22;
    EditText GetSize2;
    String[] ListElementSize2 = new String[] {};

    ExpandableHeightListView listview3;
    Button Addbutton3;
    EditText GetValue3;
    String[] ListElements3 = new String[] {};
    ExpandableHeightListView listview32;
    EditText GetSize3;
    String[] ListElementSize3 = new String[] {};

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
        GetSize1 = (EditText)findViewById(R.id.sizeTex1);
        listview12 = (ExpandableHeightListView)findViewById(R.id.listView12);


        // initialize UI elements: list view 2
        listview2 = (ExpandableHeightListView)findViewById(R.id.listView2);
        Addbutton2 = (Button)findViewById(R.id.button2);
        GetValue2 = (EditText)findViewById(R.id.editText2);
        GetSize2 = (EditText)findViewById(R.id.sizeTex2);
        listview22 = (ExpandableHeightListView)findViewById(R.id.listView22);

        // initialize UI elements: list view 3
        listview3 = (ExpandableHeightListView)findViewById(R.id.listView3);
        Addbutton3 = (Button)findViewById(R.id.button3);
        GetValue3 = (EditText)findViewById(R.id.editText3);
        GetSize3 = (EditText)findViewById(R.id.sizeTex3);
        listview32 = (ExpandableHeightListView)findViewById(R.id.listView32);

        // adapter for list 1
        final List<String> ListElementsArrayList1 = new ArrayList<String>(Arrays.asList(ListElements1));
        final List<String> ListElementSizeArrayList1 = new ArrayList<String>(Arrays.asList(ListElementSize1));

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,
                        ListElementsArrayList1);

        final ArrayAdapter<String> adapter12 = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,
                ListElementSizeArrayList1);

        listview1.setAdapter(adapter1);
        listview1.setExpanded(true);

        listview12.setAdapter(adapter12);
        listview12.setExpanded(true);

        Addbutton1.setOnClickListener(new View.OnClickListener() {
            String item1;
            int num1;

            @Override
            public void onClick(View v) {
                // get the name of the item
                item1 = GetValue1.getText().toString();
                String num1String = GetSize1.getText().toString();
                num1 = Integer.parseInt(num1String);
                jsonParse(item1, num1);

                ListElementsArrayList1.add(GetValue1.getText().toString());
                ListElementSizeArrayList1.add(GetSize1.getText().toString());

                GetValue1.getText().clear();
                GetSize1.getText().clear();

                adapter1.notifyDataSetChanged();
                adapter12.notifyDataSetChanged();
            }
        });

        // adapter for list 2
        final List<String> ListElementsArrayList2 = new ArrayList<String>(Arrays.asList(ListElements2));

        final List<String> ListElementSizeArrayList2 = new ArrayList<String>(Arrays.asList(ListElementSize2));


        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,
                ListElementsArrayList2);


        final ArrayAdapter<String> adapter22 = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,
                ListElementSizeArrayList2);

        listview2.setAdapter(adapter2);
        listview2.setExpanded(true);

        listview22.setAdapter(adapter22);
        listview22.setExpanded(true);

        Addbutton2.setOnClickListener(new View.OnClickListener() {
            String item2;
            int num2;

            @Override
            public void onClick(View v) {
                // get name for item2
                item2 = GetValue2.getText().toString();
                String num2String = GetSize2.getText().toString();
                num2 = Integer.parseInt(num2String);

                jsonParse(item2, num2);

                ListElementsArrayList2.add(GetValue2.getText().toString());
                ListElementSizeArrayList2.add(GetSize2.getText().toString());

                GetValue2.getText().clear();
                adapter2.notifyDataSetChanged();

                GetSize2.getText().clear();
                adapter22.notifyDataSetChanged();
            }
        });


        // adapter for list 3
        final List<String> ListElementsArrayList3 = new ArrayList<String>(Arrays.asList(ListElements3));

        final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,
                ListElementsArrayList3);

        final List<String> ListElementSizeArrayList3 = new ArrayList<String>(Arrays.asList(ListElementSize3));

        final ArrayAdapter<String> adapter32 = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,
                ListElementSizeArrayList3);

        listview3.setAdapter(adapter3);
        listview3.setExpanded(true);

        listview32.setAdapter(adapter32);
        listview32.setExpanded(true);

        Addbutton3.setOnClickListener(new View.OnClickListener() {
            String item3;
            int num3;

            @Override
            public void onClick(View v) {
//                queue.cancelAll(pot_curr);
                // get name for item3
                item3 = GetValue3.getText().toString();
                String num3String = GetSize3.getText().toString();
                num3 = Integer.parseInt(num3String);

                jsonParse(item3, num3);
//                Toast.makeText(SecondActivity.this, String.valueOf(calcium_curr), Toast.LENGTH_LONG).show();

                ListElementsArrayList3.add(GetValue3.getText().toString());
                ListElementSizeArrayList3.add(GetSize3.getText().toString());

                GetValue3.getText().clear();
                adapter3.notifyDataSetChanged();

                GetSize3.getText().clear();
                adapter32.notifyDataSetChanged();
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        Intent intent1 = new Intent(SecondActivity.this, SecondActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.page_2:
                        Intent intent2 = new Intent(SecondActivity.this, FifthActivity.class);
                        intent2.putExtra("name", userName);
                        startActivity(intent2);
                        break;
                }

                return true;
            }
        });
    }

    private void jsonParse(String item1, int num) {
        final String API = "api_key=81WgY3Nd86grdI3FTtaYuE1ndBqvNTjNnwXB403a";
        final String NAME_SEARCH = "&query=";
        final String URL_PREFIX = "https://api.nal.usda.gov/fdc/v1/foods/search?";
        String url = URL_PREFIX + API + NAME_SEARCH + item1;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    int flag = 0;
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("foods");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject foods = jsonArray.getJSONObject(i);
                                String food_name = foods.getString("lowercaseDescription");
//                                Toast.makeText(SecondActivity.this, food_name, Toast.LENGTH_LONG).show();
                                if (food_name.compareTo(item1) == 0) {
                                    flag = 1;
                                    JSONArray nutrients = foods.getJSONArray("foodNutrients");
                                    for (int j = 0; j < nutrients.length(); j++) {
                                        JSONObject nutrientObj = nutrients.getJSONObject(j);
                                        String nutrientName = nutrientObj.getString("nutrientName");
                                        if (nutrientName.compareTo("Calcium, Ca") == 0) {
                                            calcium_curr += nutrientObj.getInt("nutrientNumber") * num;
                                        }
                                        if (nutrientName.compareTo("Iron, Fe") == 0) {
                                            iron_curr += nutrientObj.getInt("nutrientNumber") * num;
                                        }
                                        if (nutrientName.compareTo("Magnesium, Mg") == 0) {
                                            mag_curr += nutrientObj.getInt("nutrientNumber") * num;
                                        }
                                        if (nutrientName.compareTo("Potassium, K") == 0) {
                                            pot_curr += nutrientObj.getInt("nutrientNumber") * num;
                                        }
                                        if (nutrientName.compareTo("Vitamin A, IU") == 0) {
                                            vitA_curr += nutrientObj.getInt("nutrientNumber") * num;
                                        }
                                        if (nutrientName.compareTo("Vitamin C, total ascorbic acid") == 0) {
                                            vitC_curr += nutrientObj.getInt("nutrientNumber") * num;
                                        }
                                        if (nutrientName.compareTo("Vitamin D (D2 + D3)") == 0) {
                                            vitD_curr += nutrientObj.getInt("nutrientNumber") * num;
                                        }
                                        if (nutrientName.compareTo("Vitamin E (alpha-tocopherol)") == 0) {
                                            vitE_curr += nutrientObj.getInt("nutrientNumber") * num;
                                        }
                                    }
                                }

                                if (flag == 1) {
                                    break;
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(SecondActivity.this, "Food source is not responding (USDA API)", Toast.LENGTH_LONG).show();
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
//        queue.cancelAll(request);
        queue.add(request);
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
        Intent intent = new Intent (this, FourthActivity.class);
        int calcRem = calcium_req - calcium_curr;
        int potRem = pot_req - pot_curr;
        int vitDRem = vitD_req - vitD_curr;
        int vitERem = vitE_req - vitE_curr;
        int ironRem = 0;
        int magRem = 0;
        int vitARem = 0;
        int vitCRem = 0;

        if (isFemale) {
            ironRem = iron_req_f - iron_curr;
            magRem = mag_req_f - mag_curr;
            vitARem = vitA_req_f - vitA_curr;
            vitCRem = vitC_req_f - vitC_curr;
        }
        else {
            ironRem = iron_req_m - iron_curr;
            magRem = mag_req_m - mag_curr;
            vitARem = vitA_req_m - vitA_curr;
            vitCRem = vitC_req_m - vitC_curr;
        }

        calcRem = calcRem < 0 ? 0 : calcRem;
        potRem = potRem < 0 ? 0 : potRem;
        vitDRem = vitDRem < 0 ? 0 : vitDRem;
        vitERem = vitERem < 0 ? 0 : vitERem;
        ironRem = ironRem < 0 ? 0 : ironRem;
        magRem = magRem < 0 ? 0 : magRem;
        vitARem = vitARem < 0 ? 0 : vitARem;
        vitCRem = vitCRem < 0 ? 0 : vitCRem;

        intent.putExtra("calcRem", calcRem);
        intent.putExtra("calcCurr", calcium_curr);

        intent.putExtra("potRem", potRem);
        intent.putExtra("potCurr", pot_curr);

        intent.putExtra("vitDRem", vitDRem);
        intent.putExtra("vitDCurr", vitD_curr);

        intent.putExtra("vitERem", vitERem);
        intent.putExtra("vitECurr", vitE_curr);

        intent.putExtra("ironRem", ironRem);
        intent.putExtra("ironCurr", iron_curr);

        intent.putExtra("magRem", magRem);
        intent.putExtra("magCurr", mag_curr);

        intent.putExtra("vitARem", vitARem);
        intent.putExtra("vitACurr", vitA_curr);

        intent.putExtra("vitCRem", vitCRem);
        intent.putExtra("vitCCurr", vitC_curr);

        intent.putExtra("name", userName);

        startActivity(intent);
    }

    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
