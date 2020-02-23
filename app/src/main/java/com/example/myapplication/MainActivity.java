package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    Button goButton;
    EditText urlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView =  findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://ganeshsundaram39.github.io/");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        urlText = findViewById(R.id.urlText);
        urlText.setVisibility(View.VISIBLE);

        goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when go is clicked show webView and hide goButton and urlText
                urlText.setVisibility(View.GONE);
                goButton.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });



    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
