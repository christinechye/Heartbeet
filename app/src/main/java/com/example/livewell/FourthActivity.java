package com.example.livewell;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FourthActivity extends AppCompatActivity {
    public final static String TAG = "FourthActivity";
    int sumOfNutrientsNeeded = 0;
    static String congrats = "Congratulations! You've met your daily dietary requirement!";
    static String oops = "You still need to meet your daily dietary requirement. You can see some recommended items on our next tab.";
    TextView requirementDisplay;

    private int calCurr = 0;
    private int calNeed = 0;

    private int ironCurr = 0;
    private int ironNeed = 0;

    private int magCurr = 0;
    private int magNeed = 0;

    private int potCurr = 0;
    private int potNeed = 0;

    private int vitACurr = 0;
    private int vitANeed = 0;

    private int vitCCurr = 0;
    private int vitCNeed = 0;

    private int vitDCurr = 0;
    private int vitDNeed = 0;

    private int vitECurr = 0;
    private int vitENeed = 0;

    TextView calCurrText;
    TextView calNeedText;

    TextView ironCurrText;
    TextView ironNeedText;

    TextView magCurrText;
    TextView magNeedText;

    TextView potCurrText;
    TextView potNeedText;

    TextView vitACurrText;
    TextView vitANeedText;

    TextView vitCCurrText;
    TextView vitCNeedText;

    TextView vitDCurrText;
    TextView vitDNeedText;

    TextView vitECurrText;
    TextView vitENeedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "On Create");

        setContentView(R.layout.activity_fourth);

        // initialize UI layout
        requirementDisplay = findViewById(R.id.isNutrientMet);

        calCurrText = findViewById(R.id.data22);
        calNeedText = findViewById(R.id.data23);

        ironCurrText = findViewById(R.id.data32);
        ironNeedText = findViewById(R.id.data33);

        magCurrText = findViewById(R.id.data42);
        magNeedText = findViewById(R.id.data43);

        potCurrText = findViewById(R.id.data52);
        potNeedText = findViewById(R.id.data53);

        vitACurrText = findViewById(R.id.data62);
        vitANeedText = findViewById(R.id.data63);

        vitCCurrText = findViewById(R.id.data72);
        vitCNeedText = findViewById(R.id.data73);

        vitDCurrText = findViewById(R.id.data82);
        vitDNeedText = findViewById(R.id.data83);

        vitECurrText = findViewById(R.id.data92);
        vitENeedText = findViewById(R.id.data93);

        // get and set variables

        // see if user made nutrient requirement
        sumOfNutrientsNeeded = calNeed + ironNeed + magNeed + potNeed + vitANeed + vitCNeed + vitDNeed + vitENeed;
        if (sumOfNutrientsNeeded > 0) {
            requirementDisplay.setText(oops);
        }
        else {
            requirementDisplay.setText(congrats);
        }

        calCurr = getIntent().getIntExtra("calcCurr", 0);
        calNeed = getIntent().getIntExtra("calcRem", 0);
        calCurrText.setText(String.valueOf(calCurr));
        calNeedText.setText(String.valueOf(calNeed));
        if (calNeed > 0) {
            calNeedText.setTextColor(Color.RED);
        }

        ironCurr = getIntent().getIntExtra("ironCurr", 0);
        ironNeed = getIntent().getIntExtra("ironRem", 0);
        ironCurrText.setText(String.valueOf(ironCurr));
        ironNeedText.setText(String.valueOf(ironNeed));
        if (ironNeed > 0) {
            ironNeedText.setTextColor(Color.RED);
        }

        magCurr = getIntent().getIntExtra("magCurr", 0);
        magNeed = getIntent().getIntExtra("magRem", 0);
        magCurrText.setText(String.valueOf(magCurr));
        magNeedText.setText(String.valueOf(magNeed));
        if (magNeed > 0) {
            magNeedText.setTextColor(Color.RED);
        }

        potCurr = getIntent().getIntExtra("potCurr", 0);
        potNeed = getIntent().getIntExtra("potRem", 0);
        potCurrText.setText(String.valueOf(potCurr));
        potNeedText.setText(String.valueOf(potNeed));
        if (potNeed > 0) {
            potNeedText.setTextColor(Color.RED);
        }

        vitACurr = getIntent().getIntExtra("vitACurr", 0);
        vitANeed = getIntent().getIntExtra("vitARem", 0);
        vitACurrText.setText(String.valueOf(vitACurr));
        vitANeedText.setText(String.valueOf(vitANeed));
        if (vitANeed > 0) {
            vitANeedText.setTextColor(Color.RED);
        }

        vitCCurr = getIntent().getIntExtra("vitCCurr", 0);
        vitCNeed = getIntent().getIntExtra("vitCRem", 0);
        vitCCurrText.setText(String.valueOf(vitCCurr));
        vitCNeedText.setText(String.valueOf(vitCNeed));
        if (vitCNeed > 0) {
            vitCNeedText.setTextColor(Color.RED);
        }

        vitDCurr = getIntent().getIntExtra("vitDCurr", 0);
        vitDNeed = getIntent().getIntExtra("vitDRem", 0);
        vitDCurrText.setText(String.valueOf(vitDCurr));
        vitDNeedText.setText(String.valueOf(vitDNeed));
        if (vitDNeed > 0) {
            vitDNeedText.setTextColor(Color.RED);
        }

        vitECurr = getIntent().getIntExtra("vitECurr", 0);
        vitENeed = getIntent().getIntExtra("vitERem", 0);
        vitECurrText.setText(String.valueOf(vitECurr));
        vitENeedText.setText(String.valueOf(vitENeed));
        if (vitENeed > 0) {
            vitENeedText.setTextColor(Color.RED);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        Intent intent1 = new Intent(FourthActivity.this, SecondActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.page_2:
                        Intent intent2 = new Intent(FourthActivity.this, FifthActivity.class);
                        startActivity(intent2);
                        break;
                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
