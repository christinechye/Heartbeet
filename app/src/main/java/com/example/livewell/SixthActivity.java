package com.example.livewell;

import android.os.Bundle;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class SixthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_web_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        foodID = getIntent().getStringExtra("food");
//        String url = "https://en.wikipedia.org/wiki/" + foodID;
//
//        webView = (WebView) findViewById(R.id.webview);
//        webView.getSettings().setJavaScriptEnabled(true);
//
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl(url);

    }

}
