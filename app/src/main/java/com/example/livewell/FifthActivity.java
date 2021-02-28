package com.example.livewell;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FifthActivity extends AppCompatActivity {
    String[] nutrients = {"Calcium", "Iron", "Magnesium", "Potassium", "Vitamin A", "Vitamin C", "Vitamin D", "Vitamin E"};
    ListView lView;
    ListAdapter lAdapter;

    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        lView = (ListView) findViewById(R.id.list_view);

        lAdapter = new ListAdapter(FifthActivity.this, nutrients);

        lView.setAdapter(lAdapter);

//        // list view needs an adapter to put the contents into the view
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_list_row, foodArr);
//
//        ListView listView = (ListView) findViewById(R.id.list_view);
//        listView.setAdapter(adapter);

        userName = getIntent().getStringExtra("name");

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String nutrientID;
                switch(position) {
                    case 0:
                        nutrientID = nutrients[0];
                        Intent calc = new Intent(FifthActivity.this, SixthActivity.class);
                        calc.putExtra("nutriUrl", "https://www.healthline.com/nutrition/15-calcium-rich-foods");
                        calc.putExtra("nutrient", nutrientID); // stores an object for your intent
                        startActivity(calc);
                        break;
                    case 1:
                        nutrientID = nutrients[1];
                        Intent iron = new Intent(FifthActivity.this, SixthActivity.class);
                        iron.putExtra("nutriUrl", "https://www.healthline.com/nutrition/healthy-iron-rich-foods#1.-Shellfish");
                        iron.putExtra("nutrient", nutrientID);
                        startActivity(iron);
                        break;
                    case 2:
                        nutrientID = nutrients[2];
                        Intent magnesium = new Intent(FifthActivity.this, SixthActivity.class);
                        magnesium.putExtra("nutriUrl", "https://www.healthline.com/nutrition/10-foods-high-in-magnesium#_noHeaderPrefixedContent");
                        magnesium.putExtra("nutrient", nutrientID);
                        startActivity(magnesium);
                        break;
                    case 3:
                        nutrientID = nutrients[3];
                        Intent pot = new Intent(FifthActivity.this, SixthActivity.class);
                        pot.putExtra("nutriUrl", "https://www.healthline.com/nutrition/high-potassium-foods");
                        pot.putExtra("nutrient", nutrientID);
                        startActivity(pot);
                        break;
                    case 4:
                        nutrientID = nutrients[4];
                        Intent vitA = new Intent(FifthActivity.this, SixthActivity.class);
                        vitA.putExtra("nutriUrl", "https://www.healthline.com/nutrition/foods-high-in-vitamin-a#TOC_TITLE_HDR_2");
                        vitA.putExtra("nutrient", nutrientID);
                        startActivity(vitA);
                        break;
                    case 5:
                        nutrientID = nutrients[5];
                        Intent vitC = new Intent(FifthActivity.this, SixthActivity.class);
                        vitC.putExtra("nutriUrl", "https://www.healthline.com/nutrition/vitamin-c-foods");
                        vitC.putExtra("nutrient", nutrientID);
                        startActivity(vitC);
                        break;
                    case 6:
                        nutrientID = nutrients[6];
                        Intent vitD = new Intent(FifthActivity.this, SixthActivity.class);
                        vitD.putExtra("nutriUrl", "https://www.healthline.com/nutrition/9-foods-high-in-vitamin-d");
                        vitD.putExtra("nutrient", nutrientID);
                        startActivity(vitD);
                        break;
                    case 7:
                        nutrientID = nutrients[7];
                        Intent vitE = new Intent(FifthActivity.this, SixthActivity.class);
                        vitE.putExtra("nutriUrl", "https://www.healthline.com/nutrition/foods-high-in-vitamin-e");
                        vitE.putExtra("nutrient", nutrientID);
                        startActivity(vitE);
                        break;
                }

//                Toast.makeText(getApplicationContext(),"You Selected "+ foodArr[position-1]+ " as Food",Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        Intent intent1 = new Intent(FifthActivity.this, SecondActivity.class);
                        intent1.putExtra("name", userName);
                        startActivity(intent1);
                        break;
                    case R.id.page_2:
                        break;
                    case R.id.page_3:
                        Intent intent3 = new Intent(FifthActivity.this, PostsBoardActivity.class);
                        startActivity(intent3);
                        break;
                }

                return true;
            }
        });
    }

}
