package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    Button goButton;
    EditText urlText;
    Random randomNumber = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView =  findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        String[] memes = {"https://tinyurl.com/u7b4n7s", "https://tinyurl.com/w3yskl5", "https://tinyurl.com/toarok8",
                "https://tinyurl.com/sv7l6po", "https://tinyurl.com/wfv5apo", "https://tinyurl.com/socvfo8", "https://tinyurl.com/vpegpzd",
                "https://tinyurl.com/vnny6ce", "https://tinyurl.com/u5oaa5q", "https://tinyurl.com/wmdfzgq"
        };
        generateMemeHtml(memes[randomNumber.nextInt(memes.length)]);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        urlText = findViewById(R.id.urlText);
        urlText.setVisibility(View.VISIBLE);

        goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);

        goButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //when go is clicked show webView and hide goButton and urlText

                String url = urlText.getText().toString();
                Log.w("Debug Webapp", url);
                if (url.matches("") || !isValidUrl(url)) {
                    Toast.makeText(v.getContext(), "You did not enter a url", Toast.LENGTH_SHORT).show();
                    return;
                }


                urlText.setVisibility(View.GONE);
                goButton.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                webView.loadUrl(url);
            }
        });



    }

    private void generateMemeHtml(String meme) {
        String data = "<html>" +
                "<head></head><body>" +
                "<img src='" + meme + "' " +
                "style='width:100%;height:100%'/></body</html>";
        webView.loadDataWithBaseURL("", data, "text/html", "UTF-8", "");
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        } else {
//            super.onBackPressed();
            urlText.setVisibility(View.VISIBLE);
            goButton.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
        }
    }

    private boolean isValidUrl(String urlString) {
        return Patterns.WEB_URL.matcher(urlString).matches();
    }
}
