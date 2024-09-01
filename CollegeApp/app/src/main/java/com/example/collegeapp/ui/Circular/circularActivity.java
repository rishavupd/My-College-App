package com.example.collegeapp.ui.Circular;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.example.collegeapp.R;
import com.facebook.shimmer.ShimmerFrameLayout;

public class circularActivity extends AppCompatActivity {
    private WebView webView;

    ShimmerFrameLayout shimmerFrameLayout;

    LinearLayout shimmerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);

        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerLayout = findViewById(R.id.shimmerLayout);

        webView = (WebView) findViewById(R.id.webView);
        //webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.forum.universityupdates.in/forums/vtu-circular-notifications.58/");
        shimmerFrameLayout.stopShimmer();
        shimmerLayout.setVisibility(View.GONE);

    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}