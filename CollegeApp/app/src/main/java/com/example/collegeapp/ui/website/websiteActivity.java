package com.example.collegeapp.ui.website;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.collegeapp.R;

public class websiteActivity extends AppCompatActivity {

    private WebView website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        website = (WebView) findViewById(R.id.website);
        website.loadUrl("https://btibangalore.org/");

    }
}